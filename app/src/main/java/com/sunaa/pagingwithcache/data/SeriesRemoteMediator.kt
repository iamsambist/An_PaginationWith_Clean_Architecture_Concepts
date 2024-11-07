package com.sunaa.pagingwithcache.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sunaa.pagingwithcache.data.mappers.toSeriesEntity
import com.sunaa.pagingwithcache.data.network.ApiServices
import com.sunaa.pagingwithcache.data.room.SeriesDatabase
import com.sunaa.pagingwithcache.data.room.SeriesEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SeriesRemoteMediator(
    private val seriesDatabase: SeriesDatabase,
    private val seriesApi: ApiServices
) : RemoteMediator<Int, SeriesEntity>() {

    private var currentPage = 1
    private val maxPages = 6
    private val pageSize = 25
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SeriesEntity>
    ): MediatorResult {
        kotlinx.coroutines.delay(2000L)

        return try {
            //Determine the next page to load based on LoadType
            val pageIndex = when (loadType) {
                LoadType.REFRESH -> 1.also {
                    currentPage = 1

                } // Reset to the first page on refresh
                LoadType.APPEND -> currentPage + 1
                else -> return MediatorResult.Success(endOfPaginationReached = true)

            }


            // Check if we've reached the max page limit
            if (pageIndex > maxPages) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // Fetch data from API
            val tourResponse = seriesApi.getSeriesList(offset = (pageIndex - 1) * pageSize)
            if (tourResponse.isSuccessful) {
                val series = tourResponse.body()?.data
                if (!series.isNullOrEmpty()) {
                    seriesDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            seriesDatabase.dao.clearAll()
                        }
                        val seriesEntities = series.map { it.toSeriesEntity() }
                        seriesDatabase.dao.upsertAll(seriesEntities)

                    }
                } else {
                    Log.i("error", "Null data come from API")
                }
                // Update the current page and return success
                currentPage = pageIndex
                MediatorResult.Success(endOfPaginationReached = (currentPage > maxPages))

            } else {
                MediatorResult.Error(HttpException(tourResponse))
            }


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}