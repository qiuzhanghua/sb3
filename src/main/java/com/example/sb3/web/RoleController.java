package com.example.sb3.web;

import com.example.sb3.domain.Role;
import com.example.sb3.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody Role dto) {
//        RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
        this.service.save(dto);
    }

    @GetMapping
    public Iterable<Role> list() {
        return service.list();
    }
}
