package com.org.auditing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.auditing.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>
{
}
