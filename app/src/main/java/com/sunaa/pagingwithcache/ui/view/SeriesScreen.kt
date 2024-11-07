package com.sunaa.pagingwithcache.ui.view


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ScreenMainView(mainViewModel: SeriesViewModel = hiltViewModel()) {

    val players = mainViewModel.seriesFlow.collectAsLazyPagingItems()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = players.loadState.refresh is LoadState.Loading),
        onRefresh = {
            // Trigger the refresh action
            players.refresh() // This will call the RemoteMediator's LoadType.REFRESH
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            items(players.itemCount) { index ->
                val item = players[index]
                item?.let {
                    ItemRow(it)
                }
            }
            players.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(WindowInsets.statusBars.asPaddingValues()),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item { Text("Error Loading Data") }
                    }
                }
            }
        }


    }
}

@Composable
fun ItemRow(item: SeriesUiModel) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(width = 5.dp, Color.Gray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.seriesName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(5.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Start Date = ${item.startDate}", modifier = Modifier.padding(start = 2.dp))
            Text(text = "End Date = ${item.endDate}", modifier = Modifier.padding(start = 2.dp))
        }
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text(text = "TOTAL MATCHES = ${item.matches}")
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .border(width = 2.dp, Color.Black)
            ) {
                Column(modifier = Modifier
                    .weight(3f)
                    .padding(5.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "ODI")
                        Text(text = item.odi.toString())
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "T20")
                        Text(text = item.t20.toString())
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "TEST")
                        Text(text = item.test.toString())
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "SQUAD = ${item.squads}")
                }
            }

        }

    }
}
