package com.sunaa.pagingwithcache.data.mappers

import com.sunaa.pagingwithcache.data.network.dto.Data
import com.sunaa.pagingwithcache.data.room.SeriesEntity
import com.sunaa.pagingwithcache.ui.view.SeriesUiModel

fun Data.toSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate,
        odi = odi,
        t20 = t20,
        test = test,
        squads = squads,
        matches = matches
    )
}
// Extension function to map SeriesEntity to SeriesUiModel
fun SeriesEntity.toUiModel(): SeriesUiModel {
    return SeriesUiModel(
        seriesName = this.name,
        startDate = this.startDate,
        endDate = this.endDate,
        t20 = this.t20,
        test = this.test,
        squads = this.squads,
        odi = this.odi,
        matches = this.matches
    )
}
