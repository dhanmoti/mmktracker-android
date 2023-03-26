package com.devidhan.mmktracker

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devidhan.mmktracker.models.Currency
import com.devidhan.mmktracker.ui.theme.MMKTrackerTheme
import com.devidhan.mmktracker.views.CurrenciesListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMKTrackerTheme {
                MMKTrackerApp()
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun MMKTrackerApp() {
    var currencies = mutableStateListOf<Currency>()
    fetch(currencies)
    CurrenciesListView(currencies)
}

@Composable
private fun fetch(currencies: SnapshotStateList<Currency>) {
    var db = Firebase.firestore

    db.collection("currencies")
        .document("centralbank")
        .get()
        .addOnSuccessListener { doc ->
            //currencies.add(Currency(title = "testing 11..."))
            val array = doc.get("rates") as? ArrayList<HashMap<String, String>>
            val itr = array?.iterator()
            if (itr != null) {
                while (itr.hasNext()) {
                    val c = itr.next()
                    currencies.add(
                        Currency(
                            title = c["title"],
                            code = c["code"],
                            rate = c["rate"],
                            timestamp = c["timestamp"]
                        )
                    )
                }
            }
        }
}



