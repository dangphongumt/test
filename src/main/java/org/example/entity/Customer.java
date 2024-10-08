package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Customer implements Serializable {
    private static final long serialVersionUID= 1L;

    private int id;
    private String name;
    private transient String address;

//    public String toString() {
//        return "Customer [id=" + id + ", name=" + name + ", address=" + address + "]";
//    }
}
