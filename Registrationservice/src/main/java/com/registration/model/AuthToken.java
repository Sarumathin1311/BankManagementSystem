package com.registration.model;
public class AuthToken {

    private String token;
    private String username;
    private String id ;

    public AuthToken(){

    }

    public AuthToken(String token, String username,String id){
        this.token = token;
        this.username = username;
        this.id =  id;
    }
   
    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public AuthToken(String token){
        this.token = token;
    }

 

public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

