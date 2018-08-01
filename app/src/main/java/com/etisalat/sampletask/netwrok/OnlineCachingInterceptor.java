package com.etisalat.sampletask.netwrok;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * implementation of {@link Interceptor} that uses to
 * intercepts http request to add cache control with maxAge = 3 minutes
 * to its header in online state
 */
public class OnlineCachingInterceptor implements Interceptor {

    /**
     * intercept http request
     *
     * @param chain object that contain http request, response and connection before interception
     * @return Response response after adding cache control
     */
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
