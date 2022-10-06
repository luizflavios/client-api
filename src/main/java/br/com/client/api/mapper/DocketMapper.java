package br.com.client.api.mapper;

import br.com.client.api.dto.DocketRequestDTO;
import br.com.client.api.dto.DocketResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.model.Docket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocketMapper extends GenericMapper<DocketRequestDTO, DocketResponseDTO, Docket> {
}
