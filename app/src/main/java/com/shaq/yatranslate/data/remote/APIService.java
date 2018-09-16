package com.shaq.yatranslate.data.remote;

import com.shaq.yatranslate.data.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/api/v1.5/tr.json/translate")
    @FormUrlEncoded
    Call<Post> savePost(@Field("key") String key,
                        @Field("text") String text,
                        @Field("lang") String lang);
}
