package com.natiqhaciyef.witapplication.util_module_test.helpers

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.util.classes.PaymentChoiceModel
import com.natiqhaciyef.util.R
import com.natiqhaciyef.util.common.util.helpers.PaymentMethodList
import com.natiqhaciyef.util.common.util.helpers.cardTypeToImageFinder
import com.natiqhaciyef.witapplication.util.classes.PaymentTypes
import com.natiqhaciyef.util.common.util.helpers.creditCardOffsetTranslatorCardNumber
import com.natiqhaciyef.util.common.util.helpers.creditCardOffsetTranslatorExpirationDate
import com.natiqhaciyef.util.common.util.helpers.formatExpirationDate
import com.natiqhaciyef.util.common.util.helpers.formatOtherCardNumbers
import org.junit.Test

class CardUtilTest {

    @Test
    fun `format other card numbers returns success`() {

        val transformedText = TransformedText(
            text = AnnotatedString("1234 4567 7891 2345"),
            offsetMapping = creditCardOffsetTranslatorCardNumber
        )

        val text = formatOtherCardNumbers("1234456778912345")
        assertThat(text?.text).isEqualTo(transformedText.text)
    }

    @Test
    fun `format other expiration date returns success`() {
        val text = "2107"
        val transformedText = TransformedText(
            text = AnnotatedString("21/07"),
            offsetMapping = creditCardOffsetTranslatorExpirationDate
        )

        val result = formatExpirationDate(text)
        assertThat(result).isEqualTo(transformedText)
    }


    @Test
    fun `format other card numbers with spaces between text returns error`() {
        val text = "1234 4567 7891 2345"

        val result = formatOtherCardNumbers(text)
        assertThat(result?.text).isNull()
    }

    @Test
    fun `format other card numbers with alphabetic symbols between text returns error`() {
        val text = "absc12347dk12i45"

        val result = formatOtherCardNumbers(text)
        assertThat(result?.text).isNull()
    }

    @Test
    fun `format other expiration date with slash between text returns error`() {
        val text = "21/07"

        val result = formatExpirationDate(text)
        assertThat(result?.text).isNull()
    }

    @Test
    fun `format other expiration date with spaces between text returns error`() {
        val text = "21 07"
        val result = formatExpirationDate(text)
        assertThat(result?.text).isNull()
    }

    @Test
    fun `format other expiration date with alphabetic symbols between text returns error`() {
        val text = "x1a7"

        val result = formatExpirationDate(text)
        assertThat(result?.text).isNull()
    }

    @Test
    fun `card type to image finder returns success`(){
        val visa = PaymentTypes.VISA.mainName
        val result = cardTypeToImageFinder(visa)
        assertThat(result).isEqualTo(PaymentMethodList.list[0].image)
    }

    @Test
    fun `card type to image finder without correct title returns 0`(){
        val exp = "express"
        val result = cardTypeToImageFinder(exp)
        assertThat(result).isEqualTo(0)
    }
}