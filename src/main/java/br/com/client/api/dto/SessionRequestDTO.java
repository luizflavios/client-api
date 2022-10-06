package br.com.client.api.dto;

import br.com.client.api.generic.GenericRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class SessionRequestDTO implements GenericRequestDTO {
    @NotNull
    private EntityDTO docket;
    private LocalDateTime startOfVote;
    private LocalDateTime endOfVote;
    @NotNull
    private Boolean disableLastSessionIfExists;
}
