package com.example.db;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device")
public class Item {
    @Id private Integer id;
    private String name;
    private Integer price;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
