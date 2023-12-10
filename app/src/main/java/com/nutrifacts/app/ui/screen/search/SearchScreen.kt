package com.nutrifacts.app.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nutrifacts.app.R
import com.nutrifacts.app.di.Injection
import com.nutrifacts.app.ui.factory.ProductViewModelFactory

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProductViewModelFactory(Injection.provideProductRepository(LocalContext.current))
    ),
    navigateToDetail: (String) -> Unit
) {
    SearchContent(viewModel = viewModel, navigateToDetail = navigateToDetail, modifier = modifier)
}

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    navigateToDetail: (String) -> Unit
) {
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val query by viewModel.query
        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = {},
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text(text = stringResource(id = R.string.menu_search)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = stringResource(
                    id = R.string.menu_search
                )
            )
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    )
}