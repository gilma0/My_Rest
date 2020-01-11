package com.example.myrest;

import java.util.Calendar;

public class order {

    private String userID;
    private String itemID;
    private String time;
    private String itemName;

    public order(){

    }
    public order(String userID, String itemID, String itemName){
        this.itemID = itemID;
        this.userID = userID;
        this.time = Calendar.getInstance().getTime().toString();
        this.itemName = itemName;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
