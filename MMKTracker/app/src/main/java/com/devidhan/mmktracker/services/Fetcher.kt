package com.devidhan.mmktracker.services

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.devidhan.mmktracker.models.Currency
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun Fetcher(currencies: SnapshotStateList<Currency>) {
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
                            shortTitle = c["code"],
                            rate = c["rate"],
                            timestamp = c["timestamp"],
                            base = c["base"]
                        )
                    )
                }
            }
        }
}
