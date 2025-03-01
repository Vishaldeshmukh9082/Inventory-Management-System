package com.inventorymanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entities.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

     public Category findByCategoryId(Integer categoryId);
     public Category findByCategoryName( String categoryName);

 @Modifying
    @Query("Update Category c set c.categoryName= :categoryName, c.categoryDescription = :categoryDescription"
             + "  WHERE c.categoryId = :categoryId")
    public Integer editCategory( @Param("categoryId") Integer categoryId, 
            @Param("categoryName") String categoryName,
           @Param("categoryDescription") String categoryDescription);
    

}
