package com.wtt.composetest.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * Created by wmmeng on 2021/1/12
 *
 * @descr
 */
class OkHttpFactory private constructor() {
    private var mOkHttpClient: OkHttpClient? = null
    private var mRetrofit: Retrofit? = null
    fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().followRedirects(false)
        builder.hostnameVerifier { hostname: String?, session: SSLSession? -> true }
        setSSLSocketFactory(builder)
        return builder.build()
    }

    fun <T> createApiService(cla: Class<T>): T {
        return createRetrofit()!!.create(cla)
    }

    private fun createRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            Log.d(TAG, "mRetrofit is null！")
            mRetrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("w")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else {
            Log.d(TAG, "mRetrofit is not null！")
        }
        return mRetrofit
    }

    private val okHttpClient: OkHttpClient?
        private get() {
            if (mOkHttpClient == null) {
                Log.d(TAG, "mOkHttpClient is null！")
                mOkHttpClient = createOkHttpClient()
            } else {
                Log.d(TAG, "mOkHttpClient is not null！")
            }
            return mOkHttpClient
        }

    private fun setSSLSocketFactory(builder: OkHttpClient.Builder): Boolean {
        try {
            val sslContext = SSLContext.getInstance("TLS")
            var sslSocketFactory: SSLSocketFactory? = null
            val tm = allTrustTrustManager
            sslContext.init(arrayOf(), arrayOf<TrustManager>(tm), SecureRandom())
            sslSocketFactory = sslContext.socketFactory
            if (sslSocketFactory != null) {
                builder.sslSocketFactory(sslSocketFactory, tm)
                val DO_NOT_VERIFY = HostnameVerifier { hostname, session -> true }
                builder.hostnameVerifier(DO_NOT_VERIFY)
                return true
            }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return false
    }

    private val allTrustTrustManager: X509TrustManager
        private get() = object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        }

    private object Holder {
        val instance = OkHttpFactory()
    }

    companion object {
        private const val TAG = "OkHttpFactory"
        val instance: OkHttpFactory
            get() = Holder.instance
    }
}