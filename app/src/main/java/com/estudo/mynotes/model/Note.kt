package com.estudo.mynotes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String
): Parcelable