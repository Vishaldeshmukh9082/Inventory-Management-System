package com.inventorymanagement.helpers;

public class ResouceNotFoundException extends RuntimeException{


    public ResouceNotFoundException(String message){
        super(message);
    }

    public ResouceNotFoundException(){
        super("Resouce Not Found");
    }

}
