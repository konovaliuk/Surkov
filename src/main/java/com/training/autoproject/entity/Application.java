package com.training.autoproject.entity;


import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Entity to table application in database
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Entity
@Table(name = "\"application\"", schema = "autoproject")
public class Application implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String passnum;
    private String email;
    private String term;
    private Integer isclosed;
    private Car CarByCarId;

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
    @Column(name = "firstname")
    @Size(min = 4, message = "firstname must be more than 4")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    @Size(min = 4, message = "lastname must be more than 4")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "patronymic")
    @Size(min = 4, message = "patronimyc must be more than 4")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "passnum")
    @Size(min = 8, max = 8, message = "passnum consist of 8 values")
    @Pattern(regexp = "^(С|C)(T|Т)[0-9]+", message = "passnum has not correst serial number")
    public String getPassnum() {
        return passnum;
    }

    public void setPassnum(String passnum) {
        this.passnum = passnum;
    }

    @Basic
    @Column(name = "email")
    @Size(min = 4, message = "email must be more than 4")
    @Email(message = "not correct email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "term")
    @Pattern(regexp = "[1-9]|1[012]", message = "not correct termin")
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Basic
    @Column(name = "isclosed")
    public Integer getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(Integer isaccepted) {
        this.isclosed = isaccepted;
    }

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCarByCarId() {
        return CarByCarId;
    }

    public void setCarByCarId(Car CarByCarId) {
        this.CarByCarId = CarByCarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (passnum != null ? !passnum.equals(that.passnum) : that.passnum != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;
        if (isclosed != null ? !isclosed.equals(that.isclosed) : that.isclosed != null) return false;
        return CarByCarId != null ? CarByCarId.equals(that.CarByCarId) : that.CarByCarId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (passnum != null ? passnum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (isclosed != null ? isclosed.hashCode() : 0);
        result = 31 * result + (CarByCarId != null ? CarByCarId.hashCode() : 0);
        return result;
    }
}
