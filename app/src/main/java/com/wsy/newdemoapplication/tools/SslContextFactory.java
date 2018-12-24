package com.wsy.newdemoapplication.tools;

import android.content.Context;
import android.util.Log;

import com.wsy.newdemoapplication.R;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by WSY on 2018/6/18.
 */

public class SslContextFactory {
//    public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
//
////        if (context == null) {
////            throw new NullPointerException("context == null");
////        }
////
////        //CertificateFactory用来证书生成
////        CertificateFactory certificateFactory;
////        try {
////            certificateFactory = CertificateFactory.getInstance("X.509");
////            //Create a KeyStore containing our trusted CAs
////            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
////            keyStore.load(null, null);
////
////            for (int i = 0; i < certificates.length; i++) {
////                //读取本地证书
////                InputStream is = context.getResources().openRawResource(certificates[i]);
////                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));
////
////                if (is != null) {
////                    is.close();
////                }
////            }
////            //Create a TrustManager that trusts the CAs in our keyStore
////            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
////            trustManagerFactory.init(keyStore);
////
////            //Create an SSLContext that uses our TrustManager
////            SSLContext sslContext = SSLContext.getInstance("TLS");
////            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
////            return sslContext.getSocketFactory();
////
////        } catch (Exception e) {
////            Log.e("SslContextFactory",e.toString());
////
////        }
////        return null;
//
//
//
////        final String CLIENT_TRUST_PASSWORD = "123456";//信任证书密码，该证书默认密码是123456
////        final String CLIENT_AGREEMENT = "TLS";//使用协议
////        final String CLIENT_TRUST_KEYSTORE = "BKS";
////        SSLContext sslContext = null;
////        try {
////            //取得SSL的SSLContext实例
////            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
////            //取得TrustManagerFactory的X509密钥管理器实例
////            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
////            //取得BKS密库实例
////            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
////            InputStream is = context.getResources().openRawResource(R.raw.taobaobks);
////            try {
////                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
////            } finally {
////                is.close();
////            }
////            //初始化密钥管理器
////            trustManager.init(tks);
////            //初始化SSLContext
////            sslContext.init(null, trustManager.getTrustManagers(), null);
////        } catch (Exception e) {
////            e.printStackTrace();
////            Log.e("SslContextFactory", e.getMessage());
////        }
////        return sslContext.getSocketFactory();
//
//
//
//    }

    private static final String CLIENT_TRUST_PASSWORD = "123456";//信任证书密码
    private static final String CLIENT_AGREEMENT = "TLS";//使用协议
    private static final String CLIENT_TRUST_MANAGER = "X509";
    private static final String CLIENT_TRUST_KEYSTORE = "BKS";
    SSLContext sslContext = null;

    public SSLContext getSslSocket(Context context) {
        try {
//取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
//取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);
//取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = context.getResources().openRawResource(R.raw.taobaobks);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
//初始化密钥管理器
            trustManager.init(tks);
//初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
            Log.e("SslContextFactory", e.getMessage());
        }
        return sslContext;
    }

}
