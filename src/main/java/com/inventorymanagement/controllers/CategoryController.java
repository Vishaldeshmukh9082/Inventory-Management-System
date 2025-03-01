package com.inventorymanagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventorymanagement.entities.Category;
import com.inventorymanagement.forms.CategoryForm;
import com.inventorymanagement.services.CategoryService;

@Controller
// @RestController("/admin/category")
public class CategoryController {

    @Autowired
     private CategoryService catService;


    //  @RequestMapping("/categorypage")
    //  public String categories(){
    //     return "categorypage";
    //  }
    
    @PostMapping("/add-categories")
    public String createCategory(@ModelAttribute CategoryForm categoryForm){

         Category category=Category.builder()
        .categoryName(categoryForm.getCategoryName())
        .categoryDescription(categoryForm.getCategoryDescription())
        .build();

        Category saved=catService.AddCategory(category);
        //HttpHeaders headers = new HttpHeaders(null);
        //headers.add("Responded", "CategoryController");
        //return ResponseEntity.accepted().headers(headers).body(savedCat);
        return "redirect:admin/home";
    }
    
        @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId,@RequestBody Category cat){
         Category updatedCat = catService.editCategory(categoryId,cat);
       
        return ResponseEntity.ok(updatedCat);
    }
 @GetMapping("get-categorieslist")
 public  ResponseEntity<List<Category>> ListAllCategories(){
   List<Category> catList =  catService.ListAllCategory();
   return  ResponseEntity.ok().body(catList);
 }


 @RequestMapping("/categories/{categoryId}")
 public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId){
      Category catById = catService.searchByCategoryId(categoryId);
     return ResponseEntity.ok().body(catById);
 }


  @GetMapping("/categories-name/{categoryName}")
 public ResponseEntity<Category> getCategoryByName(@PathVariable  String categoryName){
      Category catByName = catService.findCategoryByName(categoryName);
     
       return ResponseEntity.ok().body(catByName);
 
}


// delete Category
   @RequestMapping("/categories/delete/{categoryId}")
 public String getCategoryByName(@PathVariable  Integer categoryId){
          
     catService.deleteCategory(categoryId);
     
    //  Map<String,Boolean> response = new HashMap<>();
    //   response.put("deleted", Boolean.TRUE);
    //    if(response.getValue()){

    //    }
       return "admin/home";
 
}

}
