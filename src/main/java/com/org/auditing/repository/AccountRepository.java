package com.org.auditing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.auditing.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{
}
