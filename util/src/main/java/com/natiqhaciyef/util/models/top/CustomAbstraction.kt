package com.natiqhaciyef.util.models.top

interface CustomAbstraction <T> {
    fun <T> getExactType(obj: T): String
}