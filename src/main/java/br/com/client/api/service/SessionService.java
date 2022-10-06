package br.com.client.api.service;

import br.com.client.api.dto.SessionRequestDTO;
import br.com.client.api.dto.SessionResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.generic.GenericRestService;
import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Docket;
import br.com.client.api.model.Session;
import br.com.client.api.repository.DocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class SessionService extends GenericRestService<SessionRequestDTO, SessionResponseDTO, Session> {

    @Autowired
    private DocketRepository docketRepository;

    @Autowired
    protected SessionService(JpaSpecificationRepository<Session, Integer> repository, GenericMapper<SessionRequestDTO, SessionResponseDTO, Session> genericRestMapper) {
        super(repository, genericRestMapper);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Session entity) {
        if (entity.getDisableLastSessionIfExists()) {
            disableLastSessionIfExists(entity);
        }
        entity.setEnabled(Boolean.TRUE);
        entity.setStartOfVote(entity.getStartOfVote() != null ? entity.getStartOfVote() : LocalDateTime.now());
        entity.setEndOfVote(entity.getEndOfVote() != null ? entity.getEndOfVote() : entity.getStartOfVote().plusMinutes(1));
    }

    private void disableLastSessionIfExists(Session entity) {
        Docket docket = docketRepository.findById(entity.getDocket().getId()).orElseThrow(EntityNotFoundException::new);
        docket.getSessions().forEach(session -> session.setEnabled(Boolean.FALSE));
        docketRepository.saveAndFlush(docket);
    }
}
