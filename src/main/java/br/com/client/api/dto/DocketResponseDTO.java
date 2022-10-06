package br.com.client.api.dto;

import br.com.client.api.generic.GenericResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocketResponseDTO implements GenericResponseDTO {
    private Integer id;
    private String theme;
    private LocalDateTime createdAt;
}
