package com.nutrifacts.app.ui.screen.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nutrifacts.app.R
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.response.ProductItem
import com.nutrifacts.app.di.Injection
import com.nutrifacts.app.ui.components.LinearLoading
import com.nutrifacts.app.ui.components.SmallCard
import com.nutrifacts.app.ui.factory.ProductViewModelFactory

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProductViewModelFactory(Injection.provideProductRepository(LocalContext.current))
    ),
    product: List<ProductItem> = emptyList(),
    navigateToDetail: (String) -> Unit
) {
    val context = LocalContext.current
    var loading by remember {
        mutableStateOf(false)
    }
    val result by viewModel.result.collectAsState(initial = Result.Loading)
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val query by viewModel.query
        SearchBar(
            query = query,
            onQueryChange = viewModel::searchProducts,
        )
        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
            when (result) {
                is Result.Loading -> {
                    loading = true
                }

                is Result.Success -> {
                    loading = false
                    items(product, key = { it.id!! }) { data ->
                        Log.d("success", "${data.id}")
                        SmallCard(
                            barcode = data.barcode.toString(),
                            name = data.name.toString(),
                            company = data.company.toString(),
                            navigateToDetail = navigateToDetail
                        )
                    }
                }

                is Result.Error -> {
                    loading = false
                    Toast.makeText(context, "Products could not be loaded", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        LinearLoading(isLoading = loading, modifier = modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    product: List<ProductItem>,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    loading: Boolean,
    navigateToDetail: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val query by viewModel.query
        SearchBar(
            query = query,
            onQueryChange = viewModel::searchProducts,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
//            stickyHeader {
//                SearchBar(
//                    query = query,
//                    onQueryChange = viewModel::searchProducts,
//                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
//                )
//                Box(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.secondary) // Choose a different color
//                        .padding(16.dp)
//                        .fillMaxWidth()
//                        .height(48.dp)
//                ) {
//                    Text("Debug: SearchBar is here")
//                }
//            }
            items(product, key = { it.id!! }) { data ->
                data.let {
                    SmallCard(
                        barcode = data.barcode.toString(),
                        name = data.name.toString(),
                        company = data.company.toString(),
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
        LinearLoading(isLoading = loading, modifier = modifier.align(Alignment.BottomCenter))
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