package br.com.client.api.dto;

import br.com.client.api.generic.GenericResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResponseDTO implements GenericResponseDTO {
    private Integer id;
    private String descriptionVote;
}
