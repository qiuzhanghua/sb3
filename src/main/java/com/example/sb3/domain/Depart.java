package com.example.sb3.domain;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departs")
public class Depart {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 假设唯一性，方便测试
    @Column(name = "name", unique = true, length = 128)
    private String name;
    private String description;

    @Column(name = "position")
    private Integer position = 0;
    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Depart parent;

    @OrderColumn(name = "position")
    @OneToMany(mappedBy = "parent")
    private List<Depart> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Depart getParent() {
        return parent;
    }

    public void setParent(Depart parent) {
        this.parent = parent;
    }

    public List<Depart> getChildren() {
        return children;
    }

    public void setChildren(List<Depart> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depart depart = (Depart) o;
        return Objects.equals(id, depart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Depart{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
