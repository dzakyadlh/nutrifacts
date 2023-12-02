package com.nutrifacts.app.ui.screen.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.nutrifacts.app.ui.components.SmallCard

@Composable
fun SavedScreen(modifier: Modifier = Modifier, navigateToDetail: (String) -> Unit){
    SavedContent(modifier = modifier, navigateToDetail = navigateToDetail)
}

@Composable
fun SavedContent(modifier: Modifier = Modifier, navigateToDetail:(String)->Unit){
    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
//        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)){
//
//        }
        SmallCard(barcode = "001", name = "Kitkat", company = "Nestle", navigateToDetail = navigateToDetail)
    }
}