package com.training.autoproject.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity to table user in database
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Entity
@Table(name = "\"user\"", schema = "autoproject")
public class User implements Serializable {
    private Long id;
    private String nickname;
    private String password;
    private RoleType roleType;

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
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "role_type", referencedColumnName = "id")
    public RoleType getRoleTypeById() {
        return roleType;
    }

    public void setRoleTypeById(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return roleType != null ? roleType.equals(user.roleType) : user.roleType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }
}
