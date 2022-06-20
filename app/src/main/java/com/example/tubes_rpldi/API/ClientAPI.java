package com.example.tubes_rpldi.API;

import com.example.tubes_rpldi.connection.Config;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientAPI {
    public static final String BASE_URL = Config.BASE_URL;
    private static Retrofit retrofit = null;
    private static ClientAPI instance = null;
    private InterfaceAPI myApi;

    public ClientAPI() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder client= new OkHttpClient.Builder()

                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(client.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();
        }
        myApi = retrofit.create(InterfaceAPI.class);
    }

    public static synchronized ClientAPI getInstance() {
        if (instance == null){
            instance = new ClientAPI();
        }
        return instance;
    }

    public InterfaceAPI getMyApi(){
        return myApi;
    }
}
