package com.example.sb3.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleKey implements Serializable {
    @Column(name="role_id")
    private String roleId;
    @Column(name = "user_id")
    private String userId;

    public UserRoleKey(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public UserRoleKey() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleKey that = (UserRoleKey) o;
        return roleId.equals(that.roleId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
}
