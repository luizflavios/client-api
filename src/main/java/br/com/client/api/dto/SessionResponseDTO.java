package br.com.client.api.dto;

import br.com.client.api.generic.GenericResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SessionResponseDTO implements GenericResponseDTO {
    private Integer id;
    private DocketResponseDTO docket;
    private LocalDateTime startOfVote;
    private LocalDateTime endOfVote;
    private Boolean enabled;
}
