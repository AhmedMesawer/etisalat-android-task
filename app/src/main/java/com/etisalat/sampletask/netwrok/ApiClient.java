package com.etisalat.sampletask.netwrok;

import com.etisalat.sampletask.utils.App;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    /**
     * setup http client using Retrofit
     * @return {@link Retrofit} client
     * */
    public static Retrofit getClient() {
        String BASE_URL = "https://api.androidhive.info/";
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                // setup OkHttpClient
                File cacheFile = new File(App.getContext().getCacheDir(), "responses");
                int cacheSize = 10 * 1024 * 1024;
                Cache cache = new Cache(cacheFile, cacheSize);
                OnlineCachingInterceptor onlineCachingInterceptor = new OnlineCachingInterceptor();
                OfflineCachingInterceptor offlineCachingInterceptor = new OfflineCachingInterceptor();
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(httpLoggingInterceptor)
                        .cache(cache)
                        .addInterceptor(offlineCachingInterceptor)
                        .addNetworkInterceptor(onlineCachingInterceptor)
                        .build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }
}
