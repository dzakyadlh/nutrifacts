package com.nutrifacts.app.ui.screen.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.nutrifacts.app.utils.SmallCard

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, navigateToDetail: (String) -> Unit){
    HistoryContent(modifier = modifier, navigateToDetail = navigateToDetail)
}

@Composable
fun HistoryContent(modifier: Modifier = Modifier, navigateToDetail:(String)->Unit){
    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
//        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)){
//
//        }
        SmallCard(id = "001", name = "Kitkat", company = "Nestle", navigateToDetail = navigateToDetail)
    }
}