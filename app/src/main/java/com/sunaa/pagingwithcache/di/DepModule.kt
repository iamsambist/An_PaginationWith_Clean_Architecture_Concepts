package com.sunaa.pagingwithcache.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.sunaa.pagingwithcache.data.SeriesRemoteMediator
import com.sunaa.pagingwithcache.data.network.ApiServices
import com.sunaa.pagingwithcache.data.repoimp.PagerMapper
import com.sunaa.pagingwithcache.data.room.SeriesDao
import com.sunaa.pagingwithcache.data.room.SeriesDatabase
import com.sunaa.pagingwithcache.data.room.SeriesEntity
import com.sunaa.pagingwithcache.domain.PagerMapperInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DepModule {

    private const val BASE_URL = "https://api.cricapi.com/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideNetworkInterface(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appcontext: Context): SeriesDatabase {
        return Room.databaseBuilder(
            context = appcontext,
            klass = SeriesDatabase::class.java,
            name = "cricket_series"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSeriesDao(database: SeriesDatabase): SeriesDao {
        return database.dao
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideSeriesPager(
        seriesDatabase: SeriesDatabase,
        services: ApiServices
    ): Pager<Int, SeriesEntity> {
        return Pager(
            config = PagingConfig(pageSize = 25, prefetchDistance = 1),
            remoteMediator = SeriesRemoteMediator(
                seriesDatabase = seriesDatabase,
                seriesApi = services
            ),
            pagingSourceFactory = {
                seriesDatabase.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun providePageMapper(pager: Pager<Int, SeriesEntity>): PagerMapperInterface =
        PagerMapper(pager)


}