package br.com.client.api.controller;

import br.com.client.api.dto.DocketRequestDTO;
import br.com.client.api.dto.DocketResponseDTO;
import br.com.client.api.dto.DocketResultResponseDTO;
import br.com.client.api.generic.GenericRestController;
import br.com.client.api.generic.IGenericRestService;
import br.com.client.api.generic.IGenericService;
import br.com.client.api.service.DocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dockets")
@Api(tags = "Docket")
public class DocketController extends GenericRestController<DocketRequestDTO, DocketResponseDTO> {

    @Autowired
    protected DocketController(IGenericRestService<DocketRequestDTO, DocketResponseDTO> service, IGenericService<DocketResponseDTO> superservice) {
        super(service, superservice);
    }

    @GetMapping("/result/{id}")
    @ApiOperation("Get Docket Result")
    public ResponseEntity<DocketResultResponseDTO> getDocketResult(@PathVariable Integer id) {
        return ResponseEntity.ok(((DocketService) service).getDocketResult(id));
    }

}
