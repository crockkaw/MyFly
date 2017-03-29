package com.example.kawka.myfly.network;
import android.content.Context;
import android.util.Log;

import com.example.kawka.myfly.MyApplication;
import com.example.kawka.myfly.R;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;

public  class SelfSigningClientBuilder   {


    public static OkHttpClient createClient() {

        Context context= MyApplication.getAppContext();
        OkHttpClient client = null;

        CertificateFactory cf = null;
        InputStream cert = null;
        Certificate ca = null;
        SSLContext sslContext = null;

        try {
            cf = CertificateFactory.getInstance("X.509");
            cert = context.getResources().openRawResource(R.raw.kawka); // Place your 'my_cert.crt' file in `res/raw`

            ca = cf.generateCertificate(cert);
            cert.close();

//            String alias = "myalias";
//            char[] password = "wlskeypass".toCharArray();

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .build();

        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | KeyManagementException e) {
            e.printStackTrace();
            String a = "Catch: " + e.toString();
            Log.d("myTag", a);
        }
        return client;
    }

}