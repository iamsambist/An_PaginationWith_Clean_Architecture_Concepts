package com.sunaa.pagingwithcache.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SeriesEntity (
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String,
    val startDate : String,
    val endDate : String,
    val odi : Int,
    val t20 : Int,
    val test : Int,
    val squads : Int,
    val matches : Int
)