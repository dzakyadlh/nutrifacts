package com.nutrifacts.app.ui.screen.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nutrifacts.app.R
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.model.UserModel
import com.nutrifacts.app.data.pref.UserPreference
import com.nutrifacts.app.data.pref.dataStore
import com.nutrifacts.app.ui.components.LinearLoading
import com.nutrifacts.app.ui.factory.ProductViewModelFactory
import com.nutrifacts.app.utils.DateConverter

@Composable
fun DetailScreen(
    barcode: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProductViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val context = LocalContext.current
    val currentTimeMillis = System.currentTimeMillis()
    val formattedDate = DateConverter.convertMillisToString(currentTimeMillis)
    val isSaved = remember {
        mutableStateOf(false)
    }
    val thisSavedProductId = remember {
        mutableStateOf(0)
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val user =
        UserPreference.getInstance(LocalContext.current.dataStore).getSession().collectAsState(
            initial = UserModel(0, "", false)
        ).value

    LaunchedEffect(barcode, user) {
        viewModel.getProductByBarcode(barcode)
        viewModel.getSavedProduct(user.id)
    }

    val product by viewModel.result.collectAsState()
    val savedProduct by viewModel.saved.collectAsState()

    when (product) {
        is Result.Loading -> {
            loading = true
        }

        is Result.Success -> {
            val productData = (product as Result.Success).data
            Log.d("Detail", "$productData")
            when (savedProduct) {
                is Result.Loading -> {}

                is Result.Success -> {
                    val savedProductData = (savedProduct as Result.Success).data
                    Log.d("Detail", "$savedProductData")
                    viewModel.insertHistory(
                        History(
                            name = productData.name.toString(),
                            company = productData.company.toString(),
                            photoUrl = productData.photoUrl.toString(),
                            barcode = productData.barcode.toString(),
                            user_id = user.id,
                            dateAdded = formattedDate
                        )
                    )
                    for (item in savedProductData) {
                        if (item.barcode == productData.barcode) {
                            isSaved.value = true
                            thisSavedProductId.value = item.id!!
                        }
                    }
                    Log.d("Detail", "$thisSavedProductId")
                    Box(modifier = modifier.verticalScroll(rememberScrollState())) {
                        Column(modifier = modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = productData.photoUrl ?: R.drawable.ic_launcher_background,
                                contentDescription = stringResource(id = R.string.product_img),
                                contentScale = ContentScale.FillBounds,
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
                                    Text(
                                        text = productData.name.toString(),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    if (isSaved.value) {
                                        IconButton(onClick = {
                                            viewModel.deleteSavedProduct(
                                                thisSavedProductId.value
                                            )
                                            isSaved.value = false
                                            Toast.makeText(
                                                context,
                                                "Product removed from saved",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_bookmark_24),
                                                contentDescription = stringResource(id = R.string.save)
                                            )
                                        }
                                    } else {
                                        IconButton(onClick = {
                                            viewModel.saveProduct(
                                                productData.name.toString(),
                                                productData.company.toString(),
                                                productData.photoUrl.toString(),
                                                productData.barcode.toString(),
                                                user.id
                                            )
                                            isSaved.value = true
                                            Toast.makeText(
                                                context,
                                                "Product saved",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                                                contentDescription = stringResource(id = R.string.save)
                                            )
                                        }
                                    }
                                }
                                Column {
                                    Text(
                                        text = stringResource(id = R.string.nutrition_facts),
                                        style = MaterialTheme.typography.headlineMedium
                                    )
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.calories),
                                        value = productData.calories
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.total_fat),
                                        value = productData.totalFat
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.sat_fat),
                                        value = productData.saturatedFat
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.trans_fat),
                                        value = productData.transFat
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.cholesterol),
                                        value = productData.cholesterol
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.sodium),
                                        value = productData.sodium
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.carbohydrate),
                                        value = productData.totalCarbohydrate
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.fiber),
                                        value = productData.dietaryFiber
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.sugar),
                                        value = productData.sugar
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.protein),
                                        value = productData.protein
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.vitamin_a),
                                        value = productData.vitaminA
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.vitamin_c),
                                        value = productData.vitaminC
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.vitamin_d),
                                        value = productData.vitaminD
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.calcium),
                                        value = productData.calcium
                                    )
                                    NutritionData(
                                        label = stringResource(id = R.string.iron),
                                        value = productData.iron
                                    )
                                }
                                Spacer(modifier = modifier.height(16.dp))
                                Row(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Nutrilevel",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                    if (productData.nutritionLevel != null && productData.nutritionLevel != "") {
                                        Text(
                                            text = productData.nutritionLevel.toString(),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    } else {
                                        Text(
                                            text = "N/A",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                                Text(
                                    text = "Product by ${productData.company}",
                                    style = MaterialTheme.typography.labelSmall,
                                    textAlign = TextAlign.Right,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }
                        LinearLoading(
                            isLoading = loading,
                            modifier = modifier.align(Alignment.BottomCenter)
                        )
                    }
                }

                is Result.Error -> {
                    loading = false
                    Snackbar {
                        Text(text = "Product could not be loaded")
                    }
                }
            }
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
fun NutritionData(modifier: Modifier = Modifier, label: String, value: String? = null) {
    if (value != null && value != "") {
        Column(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = label, style = MaterialTheme.typography.headlineSmall)
                Text(text = value, style = MaterialTheme.typography.bodyMedium)
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}