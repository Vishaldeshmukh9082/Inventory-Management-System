package com.inventorymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
@Builder
public class Category{
     @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name="category_id")
     private Integer categoryId;
     @Column(name ="category_name")
     private String categoryName;
     @Column(name="category_description")
     private String categoryDescription;
       @JsonIgnore
     @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private List<Product> products;
}
