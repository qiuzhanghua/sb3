package com.example.sb3.domain;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByName(String name);
}
