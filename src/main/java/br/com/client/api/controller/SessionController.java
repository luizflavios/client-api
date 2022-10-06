package br.com.client.api.controller;

import br.com.client.api.dto.SessionRequestDTO;
import br.com.client.api.dto.SessionResponseDTO;
import br.com.client.api.generic.GenericRestController;
import br.com.client.api.generic.IGenericRestService;
import br.com.client.api.generic.IGenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@Api(tags = "Session")
public class SessionController extends GenericRestController<SessionRequestDTO, SessionResponseDTO> {

    @Autowired
    protected SessionController(IGenericRestService<SessionRequestDTO, SessionResponseDTO> service, IGenericService<SessionResponseDTO> superservice) {
        super(service, superservice);
    }
}
