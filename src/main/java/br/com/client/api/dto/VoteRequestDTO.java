package br.com.client.api.dto;

import br.com.client.api.generic.GenericRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class VoteRequestDTO implements GenericRequestDTO {
    @NotNull
    private EntityDTO session;
    @NotEmpty
    private String descriptionVote;
    @NotNull
    private EntityDTO associate;
}
