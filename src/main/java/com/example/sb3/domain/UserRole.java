package com.example.sb3.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @EmbeddedId
//    @AttributeOverrides({
//            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
//            @AttributeOverride(name = "roleId", column = @Column(name = "role_id"))
//    })
    private UserRoleKey key;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        this.setKey(new UserRoleKey(role.getId(), user.getId()));
    }

    public UserRole() {
    }

    public UserRoleKey getKey() {
        return key;
    }

    public void setKey(UserRoleKey key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
