package com.shaq.yatranslate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaq.yatranslate.data.model.Post;
import com.shaq.yatranslate.data.remote.APIService;
import com.shaq.yatranslate.data.remote.ApiUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView translatedText;
    private Button translateBtn;
    private APIService mAPIService;
    private TextView mResponseTv;

    private final String API_KEY = "trnsl.1.1.20180912T105410Z.9c59e0e8d5a540cb.8706f86040790cb88fe87053cc41d7781da13c57";
    private final String TAG = "main_activity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputTextEdit);
        translatedText = findViewById(R.id.translatedTextView);
        translateBtn = findViewById(R.id.submitButton);
        mResponseTv = findViewById(R.id.tv_response);
        mAPIService = ApiUtils.getAPIService();

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = API_KEY;
                String text = inputText.getText().toString().trim();
                String lang = "en-ru";
                sendPost("trnsl.1.1.20180912T105410Z.9c59e0e8d5a540cb.8706f86040790cb88fe87053cc41d7781da13c57","hello","en-ru");
            }
        });

    }

    public void sendPost(String key, String text, String lang) {
        mAPIService.savePost(key, text, lang).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {

        mResponseTv.setText(response);
    }
}
