package com.training.autoproject.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity to table role_type in database
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Entity
@Table(name = "\"role_type\"", schema = "autoproject")
public class RoleType implements Serializable {
    private Long id;
    private String role;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleType)) return false;

        RoleType roleType = (RoleType) o;

        if (id != null ? !id.equals(roleType.id) : roleType.id != null) return false;
        return role != null ? role.equals(roleType.role) : roleType.role == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
