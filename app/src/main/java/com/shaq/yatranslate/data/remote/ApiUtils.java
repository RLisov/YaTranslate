package com.shaq.yatranslate.data.remote;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://translate.yandex.ru";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
