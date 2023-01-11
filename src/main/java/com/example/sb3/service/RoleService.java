package com.example.sb3.service;

import com.example.sb3.domain.Role;
import com.example.sb3.domain.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	private final RoleRepository repository;

	public RoleService(RoleRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void save(Role role) {
		repository.save(role);
	}

	public Iterable<Role> list() {
		return repository.findAll();
	}

}
