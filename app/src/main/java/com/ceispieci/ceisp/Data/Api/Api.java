package com.ceispieci.ceisp.Data.Api;

public class Api {

    private static final String BASE_URL = "http://192.168.0.9/Tesis/public/api/";
    //private static final String BASE_URL = "http://192.168.0.9/Tesis/public/api/";


    public static ApiRoutes getApi(){
        return RetrofitClient.getClient(BASE_URL).create(ApiRoutes.class);
    }



}