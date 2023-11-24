package com.nutrifacts.app.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class SavedProducts(
    @field:PrimaryKey(autoGenerate = false)
    var id: String,

    var name: String,
    var company: String,
    var photoUrl: String,
    var barcode: String,
    val dateAdded: String,
) : Parcelable
