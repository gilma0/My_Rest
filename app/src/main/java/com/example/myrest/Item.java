package com.example.myrest;
//type,name, description, url
public class Item {
    private String typeOf;
    private String name;
    private String description;
    private String imageUrl;

    public Item(){

    }
    public Item(String type, String name, String description, String url){
        this.name = name;
        this.typeOf = type;
        this.description = description;
        this.imageUrl = url;
    }
    public String getName(){
        return name;
    }
    public String getTypeOf(){
        return typeOf;
    }
    public String getDescription(){
        return description;
    }
    public String getImageUrl(){
        return imageUrl;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTypeOf(String TypeOf){
        this.typeOf = typeOf;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setUrl(String url){
        this.imageUrl = url;
    }
}

