package com.devidhan.mmktracker.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devidhan.mmktracker.models.Currency

@Composable
fun CurrenciesListView(currencies: List<Currency>) {
    Column() {
        LazyColumn (
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(currencies){ c ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        println("item clicked!")
                    }) {

                    Spacer(modifier = Modifier.width(16.dp))

                    c.title?.let {
                        Text(text = it, modifier = Modifier.fillMaxSize(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Light)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        c.base?.let {
                            Text(text = it,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(59,135,212)
                            )
                        }
                        c.shortTitle?.let {
                            Text(text = it,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(59,135,212)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(Icons.Rounded.ArrowForward,
                            contentDescription = "Localized description")
                        Spacer(modifier = Modifier.width(16.dp))

                        c.rate?.let {
                            Text(text = "$it MMK",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(59,135,212))
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Source: forex.cbm.gov.mm",
            Modifier.padding(8.dp),
                    fontSize = 16.sp,
            fontWeight = FontWeight.Light,
        )
    }
}
