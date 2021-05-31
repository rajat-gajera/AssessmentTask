package com.example.assessmenttask;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("testData")
    Call<ResponseData> postRequest();
}
