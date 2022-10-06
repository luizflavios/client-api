package br.com.client.api.service;

import br.com.client.api.dto.AssociateRequestDTO;
import br.com.client.api.dto.AssociateResponseDTO;
import br.com.client.api.generic.GenericMapper;
import br.com.client.api.generic.GenericRestService;
import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Associate;
import br.com.client.api.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateService extends GenericRestService<AssociateRequestDTO, AssociateResponseDTO, Associate> {

    @Autowired
    protected AssociateService(JpaSpecificationRepository<Associate, Integer> repository, GenericMapper<AssociateRequestDTO, AssociateResponseDTO, Associate> genericRestMapper) {
        super(repository, genericRestMapper);
    }

    @Override
    public AssociateResponseDTO create(AssociateRequestDTO requestDTO) {
        checkAssociateRequirements(requestDTO);
        return super.create(requestDTO);
    }

    private void checkAssociateRequirements(AssociateRequestDTO requestDTO) {
        if (((AssociateRepository) repository).existsByCpf(requestDTO.getCpf())) {
            throw new IllegalStateException("Cpf already exists");
        }
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Associate entity) {
        entity.setEnabled(Boolean.TRUE);
    }
}
