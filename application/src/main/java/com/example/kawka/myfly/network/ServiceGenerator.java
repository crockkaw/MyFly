package com.example.kawka.myfly.network;

import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by kawka on 3/16/2017.
 */
public class ServiceGenerator  {

    private static final String BASE_URL = "https://kkawka.pl:8011/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
            ;
    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();

    private static CustomTrust customTrust = new CustomTrust();

    protected static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .sslSocketFactory(customTrust.getSslSocketFactory(), customTrust.getTrustManager())
                    .connectionSpecs(Collections.singletonList(spec))
                    .certificatePinner(new CertificatePinner.Builder()
                            .add("kkawka.pl", "sha256/mAcvrh34NtaCNSxr8cZMwws7Fl7ggQCp7/s8ly0IrI4=")
                            .build());

    public static void logging (){
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
    }

    public static <S> S createService(Class<S> serviceClass) {
        logging ();
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {

        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            logging ();
            return createService(serviceClass, authToken);
        }
        logging ();
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging);}

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
}