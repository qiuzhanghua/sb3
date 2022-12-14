package com.example.sb3.domain;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@CreatableEntityView
@EntityView(Role.class)
public interface RoleView {
    @IdMapping
    String getId();
    String getName();
    void setName(String name);
}
