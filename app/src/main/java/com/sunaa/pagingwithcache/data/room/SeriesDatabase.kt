package com.sunaa.pagingwithcache.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SeriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SeriesDatabase : RoomDatabase() {
    abstract val dao : SeriesDao
}