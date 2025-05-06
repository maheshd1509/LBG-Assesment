package com.example.lbgassesment.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lbgassesment.presentation.viewmodel.ProductDetailVewModel
import com.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    id: Int?
) {
    val viewModel: ProductDetailVewModel = hiltViewModel()
    var result = viewModel.productDetail.value

    LaunchedEffect(id) {
        viewModel.getProductDetailAPi(id.toString())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.product_details))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack, "backIcon",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }) { innerPadding ->

        if (result.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        result.data?.let {
            Column(
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            )
            {
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .size(200.dp)
                        .padding(8.dp),
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = ""
                )
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.align(alignment = Alignment.Start),
                    text = stringResource(id = R.string.price, it.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.description, it.description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        if (result.error.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = result.error.toString())
            }
        }
    }
}

