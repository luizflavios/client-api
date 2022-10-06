package br.com.client.api.dto;

import br.com.client.api.generic.GenericResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AssociateResponseDTO implements GenericResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cpf;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
