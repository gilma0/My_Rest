package com.example.myrest;

public class manager {
    private String user;
    private String password;

    public manager(){

    }

    public manager(String password, String user){
        this.password = password;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
