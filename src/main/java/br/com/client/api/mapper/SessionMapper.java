package br.com.client.api.mapper;

import br.com.client.api.dto.SessionRequestDTO;
import br.com.client.api.dto.SessionResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper extends GenericMapper<SessionRequestDTO, SessionResponseDTO, Session> {
}
