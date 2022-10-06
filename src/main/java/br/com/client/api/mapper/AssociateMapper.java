package br.com.client.api.mapper;

import br.com.client.api.dto.AssociateRequestDTO;
import br.com.client.api.dto.AssociateResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.model.Associate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssociateMapper extends GenericMapper<AssociateRequestDTO, AssociateResponseDTO, Associate> {
}
