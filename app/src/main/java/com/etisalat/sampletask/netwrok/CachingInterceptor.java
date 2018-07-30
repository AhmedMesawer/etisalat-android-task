package com.etisalat.sampletask.netwrok;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CachingInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        CacheControl.Builder cacheControlBuilder = new CacheControl.Builder();
        CacheControl cacheControl = cacheControlBuilder
                .maxAge(2, TimeUnit.MINUTES)
                .build();
        return originalResponse.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
