package com.sunaa.pagingwithcache.data.network.dto

data class TourDto(
    val apikey: String,
    val `data`: List<Data>,
    val info: Info,
    val status: String
)