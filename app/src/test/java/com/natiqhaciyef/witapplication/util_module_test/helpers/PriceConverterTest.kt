package com.natiqhaciyef.witapplication.util_module_test.helpers

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.util.helpers.priceConverter
import com.natiqhaciyef.util.common.util.helpers.priceValueConverter
import org.junit.Test

class PriceConverterTest {

    @Test
    fun `price converter returns success`() {
        val result = priceConverter("USD")

        assertThat(result).isEqualTo("$")
    }

    @Test
    fun `price value converter returns error`() {
        val result1 = priceValueConverter(7.0)
        val result2 = priceValueConverter(7.6)

        assertThat(result1).isEqualTo("7")
        assertThat(result2).isEqualTo("7.6")
    }

    @Test
    fun `price converter with non-correct input returns error`() {
        val result = priceConverter("USB")

        assertThat(result).isEqualTo("Currency not selected")
    }
}