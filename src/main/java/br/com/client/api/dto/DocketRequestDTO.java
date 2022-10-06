package br.com.client.api.dto;

import br.com.client.api.generic.GenericRequestDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocketRequestDTO implements GenericRequestDTO {
    @NotEmpty
    private String theme;
}
