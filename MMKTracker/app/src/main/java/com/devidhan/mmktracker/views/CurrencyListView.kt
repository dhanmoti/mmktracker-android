package com.devidhan.mmktracker.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devidhan.mmktracker.models.Currency

@Composable
fun CurrenciesListView(currencies: List<Currency>) {
    LazyColumn (
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(currencies){ c ->
            Row(modifier = Modifier.fillMaxSize().clickable {
                println("item clicked!")
            }) {
                c.title?.let {
                    Text(text = it, modifier = Modifier.fillMaxSize(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light)
                }
                c.rate?.let {
                    Text(text = it, modifier = Modifier.fillMaxSize(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light)
                }
            }
        }
    }
}
