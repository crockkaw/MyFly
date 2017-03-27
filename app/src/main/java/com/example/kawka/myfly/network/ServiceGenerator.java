package com.example.kawka.myfly.network;

import android.content.Context;
import android.text.TextUtils;

import com.example.kawka.myfly.MyApplication;
import com.mklimek.sslutilsandroid.SslUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import okhttp3.CertificatePinner;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertTrue;

/**
 * Created by kawka on 3/16/2017.
 */

public class ServiceGenerator  {


    private static final String BASE_URL = "https://81.18.213.35:8011/rest/api/v0/";


//    private static OkHttpClient client = new OkHttpClient.Builder()
//            .certificatePinner(new CertificatePinner.Builder()
//    .add("81.18.213.35:8011", "sha1/AAAAAAAAAAAAAAAAAAAAAAAAAAA=")
//    .build())
//            .build();





    private static SelfSigningClientBuilder selfSigningClientBuilder = new SelfSigningClientBuilder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(selfSigningClientBuilder.createClient())
            ;

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

//    Context context= MyApplication.getAppContext();
//
//    SSLContext sslContext = SslUtils.getSslContextForCertificateFile(context, "raw/mycert.cer");


     protected static OkHttpClient.Builder httpClient =
            new OkHttpClient
                    .Builder();

//    httpClient.sslSocketFactory(sslContext.getSocketFactory(),
//            new X509TrustManager() {
//        @Override
//        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        @Override
//        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        @Override
//        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//            return new java.security.cert.X509Certificate[]{};
//        }
//    });


//    OkHttpClient client = new OkHttpClient();
//    client.setSslSocketFactory(sslContext.getSocketFactory());

//    File certificate = new File(certificateUri);
//    static String peerCertificate = PeerCertificateExtractor.extract(certificate);

//    private static OkHttpClient.Builder httpClient =
//            new OkHttpClient.Builder().certificatePinner(new CertificatePinner.Builder()
//                    .add("81.18.213.35:8011", peerCertificate)
//                    .build());




//    httpClient.sslSocketFactory(sslContext.getSocketFactory(),
//            new X509TrustManager() {
//        @Override
//        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws
//        CertificateException {
//        }
//
//        @Override
//        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        @Override
//        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//            return new java.security.cert.X509Certificate[]{};
//        }
//    });


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