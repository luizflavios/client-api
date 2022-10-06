package br.com.client.api.controller;

import br.com.client.api.dto.AssociateRequestDTO;
import br.com.client.api.dto.AssociateResponseDTO;
import br.com.client.api.generic.GenericRestController;
import br.com.client.api.generic.IGenericRestService;
import br.com.client.api.generic.IGenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associates")
@Api(tags = "Associate")
public class AssociateController extends GenericRestController<AssociateRequestDTO, AssociateResponseDTO> {


    @Autowired
    protected AssociateController(IGenericRestService<AssociateRequestDTO, AssociateResponseDTO> service, IGenericService<AssociateResponseDTO> superservice) {
        super(service, superservice);
    }
}
