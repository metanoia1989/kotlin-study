package com.example.jetpackcomposeexample.network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// 创建一个不安全的 OkHttpClient（仅用于开发/测试）
private fun getUnsafeOkHttpClient(): OkHttpClient {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())

    // 添加日志拦截器
    val loggingInterceptor = HttpLoggingInterceptor{ message ->
        Log.d("OkHttp", "API Call: $message")
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY  // 打印请求和响应的所有内容
    }

    return OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)  // 添加拦截器
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()
}

// 添加自定义 Json 配置 
val json = Json {
    ignoreUnknownKeys = true  // 忽略 JSON 中未知的键
    coerceInputValues = true  // 强制输入值（null 安全）
    isLenient = true         // 使用宽松模式解析
    prettyPrint = true
    explicitNulls = false  // 添加这行
}

// Retrofit 需要 Web 服务的基本 URI 和转换器工厂来构建 Web 服务 API。
// 该转换器会告诉 Retrofit 如何处理它从 Web 服务返回的数据。
// 在这种情况下，您希望 Retrofit 从 Web 服务获取 JSON 响应并将其作为 String 返回。
// Retrofit 有一个 ScalarsConverter，它支持字符串和其他原始类型。
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .addConverterFactory(ScalarsConverterFactory.create())  // 添加 Scalars 转换器
    .baseUrl(BASE_URL)
    .client(getUnsafeOkHttpClient())  // 添加不安全的 client
    .build()


interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>

    @GET("photos")
    suspend fun getPhotosAsString(): String  // 添加这个方法用于调试
}


// 在 Kotlin 中，对象声明用于声明单例对象。 单例模式确保创建对象的一个且只有一个实例，
// 并且具有对该对象的一个全局访问点。对象初始化是线程安全的，并在首次访问时完成。

// 对 Retrofit 对象调用 create（） 函数在内存、速度和性能方面都非常昂贵。
// 该应用只需要一个 Retrofit API 服务实例，因此您需要使用对象声明将该服务提供给应用的其余部分。
object MarsApi {
    // “延迟初始化”是指故意延迟对象创建，直到您实际需要该对象，以避免不必要的计算或使用其他计算资源。Kotlin 对延迟实例化提供一流的支持。
    val retrofitService: MarsApiService by lazy {
        // 在Kotlin中，使用 .java 来获取类名是为了与Java的反射体系兼容。
        // 在Kotlin中，MarsApiService::class 返回的是 KClass 对象，而 MarsApiService::class.java 返回的是与Java兼容的 Class 对象。
        // 这个 Class 对象是 Java 的 Class<T> 类型，通常在需要与 Java 库交互时使用，因为许多 Java 库的方法参数或返回类型是 Class<T>。
        // .java 是为了与 Java 的反射机制兼容，并正确地与 Java 库进行交互。
        retrofit.create(MarsApiService::class.java)
    }
}
