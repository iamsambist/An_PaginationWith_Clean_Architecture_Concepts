package com.sunaa.pagingwithcache.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SeriesDao {

    @Upsert
    suspend fun upsertAll(series: List<SeriesEntity>)

    @Query("select * from series")
    fun pagingSource(): PagingSource<Int, SeriesEntity>

    @Query("delete from series")
    suspend fun clearAll()
}