package br.com.client.api.dto;

import br.com.client.api.generic.GenericRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AssociateRequestDTO implements GenericRequestDTO {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String cpf;
}
