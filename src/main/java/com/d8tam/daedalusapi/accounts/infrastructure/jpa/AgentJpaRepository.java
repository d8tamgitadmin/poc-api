package com.d8tam.daedalusapi.accounts.infrastructure.jpa;

import com.d8tam.daedalusapi.accounts.domain.models.Agent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgentJpaRepository extends CrudRepository<Agent, Long> {

    @Override
    List<Agent> findAll();

    @Query("select a from Agent a where a.accountId=:accountId")
    List<Agent> findByAccountId(Long accountId);
}
