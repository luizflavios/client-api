package br.com.client.api.repository;

import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Associate;

public interface AssociateRepository extends JpaSpecificationRepository<Associate, Integer> {
    boolean existsByCpf(String cpf);
}
