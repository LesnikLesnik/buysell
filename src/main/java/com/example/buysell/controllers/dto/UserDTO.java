package com.example.buysell.controllers.dto;

import com.example.buysell.entity.Image;
import com.example.buysell.entity.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data

public class UserDTO {


    private Long id;

    private String email;

    private String phoneNumber;

    private String name;

    private Image avatar;

    private List<Product> products;

    public UserDTO(Long id, String email, String phoneNumber, String name, Image avatar, List<Product> products) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.avatar = avatar;
        this.products = products;
    }
}
