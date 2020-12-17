package com.example.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "PRODUCT")
public class Product {

    @Id
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    //    @JoinColumn(name ="contain_articles",referencedColumnName = "id")
    private List<Article> contain_articles = new ArrayList<>();
}
