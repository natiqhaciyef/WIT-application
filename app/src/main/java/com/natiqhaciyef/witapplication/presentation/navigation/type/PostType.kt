package com.natiqhaciyef.witapplication.presentation.navigation.type

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel

class PostType(override val isNullableAllowed: Boolean) :
    NavType<MappedPostModel>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): MappedPostModel? {
        return bundle.getBundle(key) as MappedPostModel?
    }

    override fun parseValue(value: String): MappedPostModel {
        return Gson().fromJson(value, MappedPostModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: MappedPostModel) {
        bundle.putParcelable(key, value)
    }
}