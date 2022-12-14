package com.example.myretrofitdemo.Interface;
import static com.example.myretrofitdemo.utils.ApiUtilities.API_KEY;
import android.media.Image;

import com.example.myretrofitdemo.model.ImageModel;
import com.example.myretrofitdemo.model.Search;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("Authorization: Client_ID"+API_KEY)
    @GET("/photos")
    Call<List<ImageModel>> getImage(@Query("page") int page, @Query("per_page")int per_page);

    @Headers("Authorization: Client_ID"+API_KEY)
    @GET("/search/photo")
    Call<Search> searchImage(@Query("query") String query);

}
