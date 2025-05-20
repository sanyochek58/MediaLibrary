package com.example.homemedialibrary.API;

import android.provider.MediaStore;

import com.example.homemedialibrary.DTO.LoginDTO;
import com.example.homemedialibrary.DTO.MediaLibDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/auth/login")
    Call<ResponseBody> login(@Body LoginDTO dto);

    @GET("api/media")
    Call<List<MediaLibDTO>> getAllMedia();

    @GET("api/media/type/{type}")
    Call<List<MediaLibDTO>> getAllMediaByTypes(@Path("type")String type);
}
