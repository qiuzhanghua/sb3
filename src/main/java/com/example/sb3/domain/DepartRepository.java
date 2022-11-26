package com.example.sb3.domain;

import org.springframework.data.repository.CrudRepository;

public interface DepartRepository extends CrudRepository<Depart, String> {
    Depart findByName(String name);
}
