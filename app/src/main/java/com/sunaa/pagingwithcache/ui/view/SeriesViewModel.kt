package com.sunaa.pagingwithcache.ui.view

import androidx.lifecycle.ViewModel
import com.sunaa.pagingwithcache.domain.PagerMapperInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    pagerMapperInterface: PagerMapperInterface
) : ViewModel() {
    val seriesFlow = pagerMapperInterface.getSeriesStream()

}