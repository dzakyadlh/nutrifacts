package com.nutrifacts.app.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nutrifacts.app.R
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.local.entity.SavedProducts
import com.nutrifacts.app.ui.factory.ProductViewModelFactory

@Composable
fun DetailScreen(
    barcode: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProductViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val isSaved = remember {
        mutableStateOf(false)
    }
    var loading by remember {
        mutableStateOf(false)
    }
    var savedProducts: SavedProducts? = null

    LaunchedEffect(barcode) {
        viewModel.getProductByBarcode(barcode)
    }

    val product by viewModel.result.collectAsState()

    when (product) {
        is Result.Loading -> {
            loading = true
        }

        is Result.Success -> {
            val thisProduct = (product as Result.Success).data
            DetailContent(
                name = thisProduct.name.toString(),
                company = thisProduct.company.toString(),
                barcode = thisProduct.barcode.toString(),
                nutrilevel = thisProduct.nutritionLevel.toString(),
                isSaved = false,
                onSaveClick = {},
                modifier
                )
        }

        is Result.Error -> {
            loading = false
            Snackbar {
                Text(text = "Product could not be loaded")
            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    company: String,
    barcode: String,
    nutrilevel: String,
    isSaved: Boolean,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    photoUrl: String? = null
) {
    Box(modifier = modifier) {
        Column(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                model = photoUrl ?: R.drawable.ic_launcher_background,
                contentDescription = stringResource(id = R.string.product_img),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = name, style = MaterialTheme.typography.titleMedium)
                    if (nutrilevel == "A") Text(
                        text = nutrilevel,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else if (nutrilevel == "B") Text(
                        text = nutrilevel,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else if (nutrilevel == "C") Text(
                        text = nutrilevel,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else if (nutrilevel == "D") Text(
                        text = nutrilevel,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else if (nutrilevel == "E") Text(
                        text = nutrilevel,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else Text(text = "-", style = MaterialTheme.typography.titleLarge)
                }
                Text(
                    text = "Product by $company",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Right,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}