package com.example.finallabmobile2024.API.Api;

import com.example.finallabmobile2024.Models.APOD;

import java.security.PublicKey;
import java.util.List;

public class Response {
    public APOD data;
    public APOD getData(){
        return data;
    }
    public void setData(APOD data){
        this.data=data;
    }
}
