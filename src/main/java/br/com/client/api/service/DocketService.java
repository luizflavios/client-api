package br.com.client.api.service;

import br.com.client.api.dto.DocketRequestDTO;
import br.com.client.api.dto.DocketResponseDTO;
import br.com.client.api.dto.DocketResultResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.generic.GenericRestService;
import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Docket;
import br.com.client.api.model.Session;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static br.com.client.api.service.VoteService.NAO;
import static br.com.client.api.service.VoteService.SIM;

@Service
public class DocketService extends GenericRestService<DocketRequestDTO, DocketResponseDTO, Docket> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    protected DocketService(JpaSpecificationRepository<Docket, Integer> repository, GenericMapper<DocketRequestDTO, DocketResponseDTO, Docket> genericRestMapper) {
        super(repository, genericRestMapper);
    }

    @Transactional(readOnly = true)
    public DocketResultResponseDTO getDocketResult(Integer id) {
        Docket docket = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        Integer yesCount = getSpecificCount(docket, SIM);
        Integer noCount = getSpecificCount(docket, NAO);
        String winner = getWinner(yesCount, noCount);

        DocketResultResponseDTO result = DocketResultResponseDTO.builder()
                .voteCount(getVoteCount(docket))
                .yesCount(yesCount)
                .noCount(noCount)
                .winner(winner)
                .build();

        rabbitTemplate.convertAndSend(this.queue.getName(), result.toString());
        return result;
    }

    private String getWinner(Integer yesCount, Integer noCount) {
        if (yesCount.equals(noCount)) {
            return "Empate";
        } else if (yesCount > noCount) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    private Integer getSpecificCount(Docket docket, String description) {
        int count = 0;
        for (Session session : docket.getSessions()) {
            count += session.getVotes().stream().filter(vote -> vote.getDescriptionVote().equals(description)).count();
        }
        return count;
    }

    private Integer getVoteCount(Docket docket) {
        int count = 0;
        for (Session session : docket.getSessions()) {
            count += session.getVotes().size();
        }
        return count;
    }
}
