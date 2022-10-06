package br.com.client.api.repository;

import br.com.client.api.generic.JpaSpecificationRepository;
import br.com.client.api.model.Vote;

public interface VoteRepository extends JpaSpecificationRepository<Vote, Integer> {
}
