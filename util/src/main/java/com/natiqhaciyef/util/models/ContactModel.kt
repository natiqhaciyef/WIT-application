package com.natiqhaciyef.util.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.natiqhaciyef.util.common.util.helpers.getNow
import kotlinx.parcelize.Parcelize

@Entity(tableName = "contact_table")
@Parcelize
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val description: String,
    val field: String,
    val date: String = getNow()!!,
    val isChecked: Boolean = false
): Parcelable
