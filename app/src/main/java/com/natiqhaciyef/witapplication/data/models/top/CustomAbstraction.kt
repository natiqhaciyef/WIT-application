package com.natiqhaciyef.witapplication.data.models.top

interface CustomAbstraction <T> {
    fun <T> getExactType(obj: T): String
}