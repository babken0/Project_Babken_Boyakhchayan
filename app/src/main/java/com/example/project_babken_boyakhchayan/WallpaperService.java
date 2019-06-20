package com.example.project_babken_boyakhchayan;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WallpaperService {


    @GET("?key=11709270-144266925744e029da99a9d3f&image_type=photo")
    Single<CategoryWallpaperList> getCategory(@Query("q") String category);


}
