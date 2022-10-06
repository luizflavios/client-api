package br.com.client.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class DocketResultResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer voteCount;
    private Integer yesCount;
    private Integer noCount;
    private String winner;
}
