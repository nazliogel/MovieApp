package com.example.movie_app.movieapp.di

import android.util.Log
import com.example.movie_app.data.web.repository.MovieListNetworkRepositoryImpl
import com.example.movie_app.data.web.repository.UpComingMovieListNetworkRepositoryImpl
import com.example.movie_app.data.web.service.MovieServices
import com.example.movie_app.domain.repository.UpComingMovieListNetworkRepository
import com.example.movie_app.utils.Constant
import com.example.movie_app.utils.Constant.BASE_URL
import com.squareup.leakcanary.core.BuildConfig
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

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttp)
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Singleton
    @Provides
    fun provideRestOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor { message ->
                    try {
                        if (BuildConfig.DEBUG) {
                            Log.d("okhttp", message)
                        }
                    } catch (e: Exception) {
                    }
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideMovieServices(retrofit: Retrofit): MovieServices {
        return retrofit.create(MovieServices::class.java)
    }

    @Singleton
    @Provides
    fun provideUpComingMovieListNetworkRepository(movieServices: MovieServices): UpComingMovieListNetworkRepository {
        return UpComingMovieListNetworkRepositoryImpl(movieServices)
    }

}
