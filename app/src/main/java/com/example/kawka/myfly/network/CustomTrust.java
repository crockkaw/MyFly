package com.example.kawka.myfly.network;

import android.content.Context;

import com.example.kawka.myfly.MyApplication;
import com.example.kawka.myfly.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CertificatePinner;

import okio.Buffer;

public final class CustomTrust {

    X509TrustManager trustManager;
    SSLSocketFactory sslSocketFactory;

    public CustomTrust() {

        Context context= MyApplication.getAppContext();
        InputStream caInput = context.getResources().openRawResource(R.raw.root_ca);


        try {
            trustManager = trustManagerForCertificates(caInput);

//            trustManager = trustManagerForCertificates(trustedCertificatesInputStream());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e ) {
            throw new RuntimeException(e);

        }

    }

    public X509TrustManager getTrustManager() {
        return trustManager;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }


    /**
     * Returns an input stream containing one or more certificate PEM files. This implementation just
     * embeds the PEM files in Java strings; most applications will instead read this from a resource
     * file that gets bundled with the application.
     */
    private InputStream trustedCertificatesInputStream() {
        // PEM files for root certificates of Comodo and Entrust. These two CAs are sufficient to view
        // https://publicobject.com (Comodo) and https://squareup.com (Entrust). But they aren't
        // sufficient to connect to most HTTPS sites including https://godaddy.com and https://visa.com.
        // Typically developers will need to get a PEM file from their organization's TLS administrator.
        String CertificationAuthority = ""
                + "-----BEGIN CERTIFICATE-----\n"
                + "MIIFsjCCA5qgAwIBAgIJAIxqELbmTOHcMA0GCSqGSIb3DQEBCwUAMEQxCzAJBgNV\n"
                + "BAYTAlBMMRMwEQYDVQQIEwpTb21lLVN0YXRlMQ0wCwYDVQQKEwRJVFdMMREwDwYD\n"
                + "VQQDEwhUdXItQ2VydDAeFw0xNzAzMjkxNTIzNThaFw0yNzAzMjcxNTIzNThaMEQx\n"
                + "CzAJBgNVBAYTAlBMMRMwEQYDVQQIEwpTb21lLVN0YXRlMQ0wCwYDVQQKEwRJVFdM\n"
                + "MREwDwYDVQQDEwhUdXItQ2VydDCCAiIwDQYJKoZIhvcNAQEBBQADggIPADCCAgoC\n"
                + "ggIBAM0DTYR/gLkwQLgtAToLD3S15cDRsXM0GWSUyfxVLoDDYtSxShAKjhBE2L+s\n"
                + "4xuFuiHHYiiKApWt3SRzHX+711kfMvOIv9p3+2UuY2AaDqmRVpBB/7x2nV5dTYX/\n"
                + "3IHiCi7sCobPTGGPUEoaYN/B7dATYb+2ZFDA1Hz0tuZssiVoF85+A7/rhXTUl8Ic\n"
                + "t63KEfLQ1djMNQjldY6OeCmSU04eZ+F363TB8Koc3RkwATB68X81RLk4ICYtKW1K\n"
                + "nt9EkzXh9WyvG6cmpf49iBfEY2p1cRS8Xh3CRlHcSuEN3oGbp8c11E4F+/cgqI3h\n"
                + "9sAfTChtJaNiWyEYJgky54BxDvhOw4E87hGW6mF6RCAdJI5x2Q/yyFBUYukrW50x\n"
                + "+wZKQOmpiZ37KLaVtBWpELd7lgPKJGV2qAWcE7i9jBCgaKoJ1cgStKLAkkIL/Zm2\n"
                + "OYvNoZJGg5Oxt51r7p181Grnu6mXTc6qUgIo9ooeTDfD5feXqDRw+rEhwY1nyylB\n"
                + "wCbzSOu3eJqUZn2UEK0tE2gVF2rTyWeCWeGqpVlfvm9DAM9DTiBex+CdFyFBVss+\n"
                + "mvzIWRJleJ+09UH2/QHMUC+YMAXF/rItTUpsPgPn5KhbKKiI0a2Pkpq264eLPwNB\n"
                + "sLH/N3z2BHJMZyU0xQmUvs17veDw15ie7HI0/mboJ+jGI2XDAgMBAAGjgaYwgaMw\n"
                + "HQYDVR0OBBYEFHxoisNwPSuFUvQGhu0L/Uty6jCnMHQGA1UdIwRtMGuAFHxoisNw\n"
                + "PSuFUvQGhu0L/Uty6jCnoUikRjBEMQswCQYDVQQGEwJQTDETMBEGA1UECBMKU29t\n"
                + "ZS1TdGF0ZTENMAsGA1UEChMESVRXTDERMA8GA1UEAxMIVHVyLUNlcnSCCQCMahC2\n"
                + "5kzh3DAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBCwUAA4ICAQAzN7i8ufuTwahv\n"
                + "DRCbNPkVOwtEY3Jdrxzzt+JVhYcbLbR3KhpN4SCf2jM3KtajW7NBPt8GSOLXPAuU\n"
                + "vuRJA1pjRjWtwE1h+MB/yCKr6h1OPwEsm/U7CLiLE7mIbL8vPcQHwH7WQpbXM7uf\n"
                + "oNhNDhr0wJlD1udYsON06qfkai9r88NHuD5U/tHRLduDHtzZRtKVpYUyjB5vLnI9\n"
                + "ME87tzGwrbwjzrfJNYLNAtLXAelh7QZYQTYeMDT3xM/NCDiRdB0QeIrZ1XO0EAFt\n"
                + "w8QskTnKnxHms9AZCLS24BRmviiO97RHzuIF2vgLiBX41FyIGQt7RGCxZbZCN/J1\n"
                + "GqDQMBGbqOYWrqffV8TzDK9fzt3fP1AdIqxfdbPJrG7qynvxAdLt8zlSit1ZnLCE\n"
                + "z0c0BSYcvkR7Wg/InBrRjAAP5gTWyZws/4L/UyJH0tfdc6dH5hVJObvzqnr/YMgo\n"
                + "/UBKZq1fCLvIakpskxFy73hK9I82bpsnJ/9GzoznhSKDLFlnHRT63tp7XZvv68ff\n"
                + "wZcQxmamnWVaNdiVS+SgbjsBtDvPrqLXCiLtVtFS/E9WImVUfx5OHtgxJADEA7ZF\n"
                + "0OwpxbORyfAa9QBx0P2+uEoaEro4cRdgSY3Unll98CeVFnbKknH1OPYkvTNtcT7g\n"
                + "8vih8bPEYjEycZJUSMnA2AHbMvNjNw==\n"
                + "-----END CERTIFICATE-----\n";

//        String entrustCertificate = ""
//                + "-----BEGIN CERTIFICATE-----\n"
//                + "MIIEGTCCAgECCQDr69IpPTw9cTANBgkqhkiG9w0BAQsFADBEMQswCQYDVQQGEwJQ\n"
//                + "TDETMBEGA1UECBMKU29tZS1TdGF0ZTENMAsGA1UEChMESVRXTDERMA8GA1UEAxMI\n"
//                + "VHVyLUNlcnQwHhcNMTcwMzI5MTkwMzUzWhcNMjcwMzI2MTkwMzUzWjBZMQswCQYD\n"
//                + "VQQGEwJQTDETMBEGA1UECBMKU29tZS1TdGF0ZTEhMB8GA1UEChMYSW50ZXJuZXQg\n"
//                + "V2lkZ2l0cyBQdHkgTHRkMRIwEAYDVQQDEwkxMjcuMC4wLjEwggEiMA0GCSqGSIb3\n"
//                + "DQEBAQUAA4IBDwAwggEKAoIBAQDcPa+ruuFs9KNB4to9lLn//v7Zjki+reqahnhZ\n"
//                + "3NLXGEPlU3/gZt6WsP6QXH/cW0gat4MydEoEV8oafgMLKVfSqiG+qDGQNBbXn9mJ\n"
//                + "ISG4z9arTBhgHo3b6YvpG8EsM8foCFWfYxN8RN8vTRB/ziK+tck5dlpz8sFFyli7\n"
//                + "w3sYmSmu+h7QjokJyHeSxO22Ubv+e7PHxLYTdhcmwixwgivwgV5dt8tDU84TlM/k\n"
//                + "LZ5oSfL8I7eBP+Vd7/tSz42jqYe2k9czYc0/CwsYvE5nKREuFQxcgGzHRi/htqOO\n"
//                + "41YJYTupSfhQsr8QtUg2zXayZyRYia8hn0f4pVATaC6Uc7epAgMBAAEwDQYJKoZI\n"
//                + "hvcNAQELBQADggIBAK3LDYz4jFHie0BkQt3AHP69jLA7rc8BWq7COXLk0j28uC2V\n"
//                + "R7woN00PF3y/URBJQX0RoNW627/RM/nWi/8A93pvozAfBPsFXs+/8qTUR/pytcto\n"
//                + "L+H651V0KczPrJU00HCXvvZ+v/MHFWHVPpreS6otXckOgiWTravUyKnN9YcJn+vt\n"
//                + "MZmRUxzPrcOkfZICZNEGYFbYFbcpHJ8tBiTpgfXMXsG+AagN9n6qyl/vsEkMBjI1\n"
//                + "zIPc9XNMigoWI+eXK7SDSYv00mjVdkOrKfl9lRsmYJc96LjE8HJcKyr/gcUymDjh\n"
//                + "BOn+vVg3Jc82kpFG5nUT33g3ntsxmQC570RNkFYUyoPeJYiBY0L6cQZ64f2H1K+z\n"
//                + "jz3KukL1rtMufgk9rQ4tvsu2DikoORT29DqgVqaV9ZmZIZ0bnptka7IFumxlV6MC\n"
//                + "ZqwZbY0DnZo72iMW9KYMFUCUSkgYkEjMwUrieyzEV6Y+VL7CWkhbrPnG+Qw+k5lB\n"
//                + "O7EGP7Ii8Q3KnMRKsx4skbnc6Pznd2KAwJqLRhkbeTkdvB2vibT2VNg7wI4cH+eT\n"
//                + "nYfaKRHCnVKY8VOEUNxvsnbznWy98KlkqnA+PXmlBKQQerkUfPy0vnO8dL0ZL9Ac\n"
//                + "GRpBa1WXUleHlGE+GbhlS/FJkGWRYLVRExFVXLwUtHbGYq+8HzwIAveMt3Fr\n"
//                + "-----END CERTIFICATE-----\n";

//
        return new Buffer()
                .writeUtf8(CertificationAuthority)
//                .writeUtf8(entrustCertificate)
                .inputStream();
    }


    /**
     * Returns a trust manager that trusts {@code certificates} and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a {@code
     * SSLHandshakeException}.
     *
     * <p>This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     *
     * <p>See also {@link CertificatePinner}, which can limit trusted certificates while still using
     * the host platform's built-in trust store.
     *
     * <h3>Warning: Customizing Trusted Certificates is Dangerous!</h3>
     *
     * <p>Relying on your own trusted certificates limits your server team's ability to update their
     * TLS certificates. By installing a specific set of trusted certificates, you take on additional
     * operational complexity and limit your ability to migrate between certificate authorities. Do
     * not use custom trusted certificates in production without the blessing of your server's TLS
     * administrator.
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }



}