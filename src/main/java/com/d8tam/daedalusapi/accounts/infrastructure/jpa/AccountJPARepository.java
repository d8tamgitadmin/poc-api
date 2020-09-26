package com.d8tam.daedalusapi.accounts.infrastructure.jpa;

import com.d8tam.daedalusapi.accounts.domain.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountJPARepository extends CrudRepository<Account,Long> {
    @Override
    List<Account> findAll();

}
