package com.sunaa.pagingwithcache.domain

import androidx.paging.PagingData
import com.sunaa.pagingwithcache.ui.view.SeriesUiModel
import kotlinx.coroutines.flow.Flow

interface PagerMapperInterface {
    fun getSeriesStream(): Flow<PagingData<SeriesUiModel>>
}