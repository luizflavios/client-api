package br.com.client.api.service;

import br.com.client.api.dto.VoteRequestDTO;
import br.com.client.api.dto.VoteResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.generic.GenericRestService;
import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Session;
import br.com.client.api.model.Vote;
import br.com.client.api.repository.AssociateRepository;
import br.com.client.api.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService extends GenericRestService<VoteRequestDTO, VoteResponseDTO, Vote> {

    public static final String SIM = "Sim";
    public static final String NAO = "Não";

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private WebClientService webClientService;

    @Autowired
    protected VoteService(JpaSpecificationRepository<Vote, Integer> repository, GenericMapper<VoteRequestDTO, VoteResponseDTO, Vote> genericRestMapper) {
        super(repository, genericRestMapper);
    }

    @Override
    public VoteResponseDTO create(VoteRequestDTO requestDTO) {
        checkVoteRequirements(genericRestMapper.toEntity(requestDTO));
        return super.create(requestDTO);
    }

    private void checkVoteRequirements(Vote vote) {
        if (Boolean.FALSE.equals(webClientService.memberCanVote
                (associateRepository.findById(vote.getAssociate().getId())
                        .orElseThrow(EntityNotFoundException::new).getCpf()
                        .replace(".", "").replace("-", "").trim()))) {
            throw new IllegalStateException("Member cannot be vote! Invalid cpf");
        }

        Session session = sessionRepository.findById(vote.getSession().getId()).orElseThrow(EntityNotFoundException::new);

        if (session.getEndOfVote().isBefore(LocalDateTime.now().minusHours(3)) || session.getEndOfVote().isEqual(LocalDateTime.now().minusHours(3))) {
            throw new IllegalStateException("Vote session is finished");
        }

        if (Boolean.TRUE.equals(session.getVotes().stream()
                .anyMatch(v -> v.getAssociate().getId().equals(vote.getAssociate().getId())))) {
            throw new IllegalStateException("Vote must be unique per session");
        }

        List<String> allowedVotes = List.of(SIM, NAO);

        if (!allowedVotes.contains(vote.getDescriptionVote())) {
            throw new IllegalStateException("Vote description must be Sim/Não");
        }

    }

}
