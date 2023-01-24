/*
 * Copyright (2023) The 邱张华 Authors.
 */
package com.example.sb3.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@ManyToOne(optional = true)
	@JoinColumn(name = "depart_id", referencedColumnName = "id")
	private Depart depart;

	@OneToMany(mappedBy = "user")
	List<UserRole> roles;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "email", unique = true, length = 128)
	private String email;

	@Column(name = "password", length = 128)
	private String password;

	@CreationTimestamp
	@Column(name = "created_at", insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "deleted_at", insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public List<UserRole> getRoles() {
		return this.roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public void setUserRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public Depart getDepart() {
		return this.depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(this.id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "User{" + "id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", email='" + this.email + '\''
				+ '}';
	}

}
