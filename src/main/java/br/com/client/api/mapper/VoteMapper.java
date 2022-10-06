package br.com.client.api.mapper;

import br.com.client.api.dto.VoteRequestDTO;
import br.com.client.api.dto.VoteResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper extends GenericMapper<VoteRequestDTO, VoteResponseDTO, Vote> {
}
