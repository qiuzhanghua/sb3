package com.example.sb3.web;

import com.example.sb3.domain.Role;
import com.example.sb3.service.RoleService;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class GraphqlController {

	private final RoleService service;

	public GraphqlController(RoleService service) {
		this.service = service;
	}

	@QueryMapping
	public List<Role> roles() {
		// List<Role> all = ImmutableList.copyOf(service.list());
		// List<Role> all = IteratorUtils.toList(service.list().iterator());
		return StreamSupport.stream(service.list().spliterator(), false).collect(Collectors.toList());
	}

}
