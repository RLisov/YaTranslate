package com.shaq.yatranslate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView translated;
    private Button translateBtn;

    private final  String URL = "https://translate.yandex.ru/";
    private final String API_KEY = "trnsl.1.1.20180912T105410Z.9c59e0e8d5a540cb.8706f86040790cb88fe87053cc41d7781da13c57";
    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private RetrofitApi ApiService = retrofit.create(RetrofitApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputTextEdit);
        translated = findViewById(R.id.translatedTextView);
        translateBtn = findViewById(R.id.submitButton);

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> mapJson = new HashMap<String, String>();
                mapJson.put("key",API_KEY);
                mapJson.put("text",inputText.getText().toString());
                mapJson.put("lang","en-ru");

                Call<Object> call = ApiService.translate(mapJson);

                try {
                    Response<Object> response = call.execute();

                    Map<String, String> map = gson.fromJson(response.body().toString(),Map.class);

                    for (Map.Entry e: map.entrySet()) {
                        if (e.getKey().equals("text")) {
                            translated.setText(e.getValue().toString());
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
