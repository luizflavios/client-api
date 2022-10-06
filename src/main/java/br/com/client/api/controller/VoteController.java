package br.com.client.api.controller;

import br.com.client.api.dto.VoteRequestDTO;
import br.com.client.api.dto.VoteResponseDTO;
import br.com.client.api.generic.GenericRestController;
import br.com.client.api.generic.IGenericRestService;
import br.com.client.api.generic.IGenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@Api(tags = "Vote")
public class VoteController extends GenericRestController<VoteRequestDTO, VoteResponseDTO> {

    @Autowired
    protected VoteController(IGenericRestService<VoteRequestDTO, VoteResponseDTO> service, IGenericService<VoteResponseDTO> superservice) {
        super(service, superservice);
    }
}
