package com.freshlemonadeteamltd.aipicphotofilters.core.di

import com.freshlemonadeteamltd.aipicphotofilters.data.network.ApiService
import com.freshlemonadeteamltd.aipicphotofilters.data.network.AuthInterceptor
import com.freshlemonadeteamltd.aipicphotofilters.data.repositories.IPhotoFilterRepository
import com.freshlemonadeteamltd.aipicphotofilters.data.repositories.PhotoFilterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



private const val TIMEOUT: Long = 60
const val BASE_URL = "https://api.deeparteffects.com/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePhotoFiltersRepository(
        mainService: ApiService
    ): IPhotoFilterRepository {
        return PhotoFilterRepository(mainService)
    }

    @Provides
    @Singleton
    fun provideApiService( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun provideCustomOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

}
