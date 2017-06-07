package com.training.autoproject.entity;

import javax.persistence.*;
/**
 * Entity to table car in database
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Entity
@Table(name = "\"car\"", schema = "autoproject")
public  class Car {
    private Long id;
    private Integer price;
    private Integer isactive;
    private String make;
    private String imagePath;

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
    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "isactive")
    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (price != null ? !price.equals(car.price) : car.price != null) return false;
        if (isactive != null ? !isactive.equals(car.isactive) : car.isactive != null) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        return imagePath != null ? imagePath.equals(car.imagePath) : car.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isactive != null ? isactive.hashCode() : 0);
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
