package com.sunaa.pagingwithcache.data.repoimp

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.sunaa.pagingwithcache.data.mappers.toUiModel
import com.sunaa.pagingwithcache.data.room.SeriesEntity
import com.sunaa.pagingwithcache.domain.PagerMapperInterface
import com.sunaa.pagingwithcache.ui.view.SeriesUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PagerMapper @Inject constructor(
    val pager: Pager<Int, SeriesEntity>
) : PagerMapperInterface {

    override fun getSeriesStream(): Flow<PagingData<SeriesUiModel>> {
        return pager.flow.map { value: PagingData<SeriesEntity> ->
            value.map {
                it.toUiModel()
            }
        }
    }
}

