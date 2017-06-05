package com.training.autoproject.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.training.autoproject.util.impl.JsonDateSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Oleg on 08.05.2017.
 */
@Entity
@Table(name = "\"order\"", schema = "autoproject")
public class Order {
    private Long id;
    private Integer repaircost;
    private Date retdate;
    private Integer isclosed;
    private Application appByAppId;

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
    @Column(name = "repaircost")
    public Integer getRepaircost() {
        return repaircost;
    }

    public void setRepaircost(Integer repaircost) {
        this.repaircost = repaircost;
    }

    @Basic
    @Column(name = "retdate")
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getRetdate() {
        return retdate;
    }

    public void setRetdate(Date retdate) {
        this.retdate = retdate;
    }

    @Basic
    @Column(name = "isclosed")
    public Integer getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(Integer isclosed) {
        this.isclosed = isclosed;
    }

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    public Application getAppByAppId() {
        return appByAppId;
    }

    public void setAppByAppId(Application appByAppId) {
        this.appByAppId = appByAppId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (repaircost != null ? !repaircost.equals(order.repaircost) : order.repaircost != null) return false;
        if (retdate != null ? !retdate.equals(order.retdate) : order.retdate != null) return false;
        if (isclosed != null ? !isclosed.equals(order.isclosed) : order.isclosed != null) return false;
        return appByAppId != null ? appByAppId.equals(order.appByAppId) : order.appByAppId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (repaircost != null ? repaircost.hashCode() : 0);
        result = 31 * result + (retdate != null ? retdate.hashCode() : 0);
        result = 31 * result + (isclosed != null ? isclosed.hashCode() : 0);
        result = 31 * result + (appByAppId != null ? appByAppId.hashCode() : 0);
        return result;
    }
}

