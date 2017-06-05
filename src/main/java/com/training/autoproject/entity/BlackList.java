package com.training.autoproject.entity;

import javax.persistence.*;

/**
 * Created by Oleg on 08.05.2017.
 */
@Entity
@Table(name = "\"black_list\"", schema = "autoproject")
public class BlackList {
    private Long id;

    private String passnum;

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
    @Column(name = "passnum")
    public String getPassnum() {
        return passnum;
    }

    public void setPassnum(String passnum) {
        this.passnum = passnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackList)) return false;

        BlackList blackList = (BlackList) o;

        if (id != null ? !id.equals(blackList.id) : blackList.id != null) return false;
        return passnum != null ? passnum.equals(blackList.passnum) : blackList.passnum == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (passnum != null ? passnum.hashCode() : 0);
        return result;
    }
}
