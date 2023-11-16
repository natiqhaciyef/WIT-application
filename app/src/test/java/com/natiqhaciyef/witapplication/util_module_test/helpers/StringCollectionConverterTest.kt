package com.natiqhaciyef.witapplication.util_module_test.helpers

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.util.helpers.toSQLiteList
import com.natiqhaciyef.util.common.util.helpers.toSQLiteMutableMap
import com.natiqhaciyef.util.common.util.helpers.toSQLiteString
import org.junit.Test

class StringCollectionConverterTest {

    @Test
    fun `map to SQLite string returns success`(){
        val map = mapOf("a" to "b", "c" to "d")
        val result = map.toSQLiteString()
        val template = "a:b#c:d#"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `list to SQLite string returns success`(){
        val list = listOf("12","asop","44","joick")
        val result = list.toSQLiteString()
        val template = "12#asop#44#joick#"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `SQLite string to map with colon & number sign returns error`(){
        val map = mapOf("a" to "a:#", "c" to "d")
        val result = map.toSQLiteString().toSQLiteMutableMap()

        assertThat(result).isNotEqualTo(map)
    }

    @Test
    fun `SQLite string to list with colon & number sign returns error`(){
        val list = listOf("12","#","44","###")
        val result = list.toSQLiteString().toSQLiteList()

        assertThat(result).isNotEqualTo(list)
    }
}