package com.sunaa.pagingwithcache.ui.view

data class SeriesUiModel(
    val seriesName : String,
    val startDate : String,
    val endDate : String,
    val odi : Int,
    val t20 : Int,
    val test : Int,
    val matches : Int,
    val squads : Int
)
/*
* @Entity(tableName = "series")
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
* */
