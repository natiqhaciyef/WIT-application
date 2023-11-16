package com.natiqhaciyef.witapplication.util_module_test.mappers

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.common.mappers.toMappedExam
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import org.junit.Test

class ExamMapperTest {


    @Test
    fun `mapped to standard exam model returns success`() {
        val examModel = ExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = "{\"title\":\"Question 1\",\"variants\":[\"A\",\"B\",\"C\",\"D\"],\"correct_variant\":\"A\",\"topic\":\"Letter\",\"point\":3.0}#",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "true"
        )

        val mappedExamModel = MappedExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = listOf(DefaultImpl.questionModel),
            level = "Advanced",
            limitPoint = 80.0,
            isActive = true
        )


        val result = mappedExamModel.toExam()
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(examModel)
    }

    @Test
    fun `standard to mapped exam model returns success`() {
        val examModel = ExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = "{\"title\":\"Question 1\",\"variants\":[\"A\",\"B\",\"C\",\"D\"],\"correct_variant\":\"A\",\"topic\":\"Letter\",\"point\":3.0}#",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "true"
        )

        val mappedExamModel = MappedExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = listOf(DefaultImpl.questionModel),
            level = "Advanced",
            limitPoint = 80.0,
            isActive = true
        )


        val result = examModel.toMappedExam()
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(mappedExamModel)
    }


    @Test
    fun `mapped to standard exam model with empty title property returns error`() {
        val mappedExamModel = MappedExamModel(
            id = 0,
            title = "",
            field = "Android",
            image = "empty image",
            questions = listOf(DefaultImpl.questionModel),
            level = "Advanced",
            limitPoint = 80.0,
            isActive = true
        )

        val result = mappedExamModel.toExam()
        assertThat(result).isNull()
    }

    @Test
    fun `mapped to standard exam model with empty field property returns error`() {
        val mappedExamModel = MappedExamModel(
            id = 0,
            title = "Quiz 1",
            field = "",
            image = "empty image",
            questions = listOf(DefaultImpl.questionModel),
            level = "Advanced",
            limitPoint = 80.0,
            isActive = true
        )

        val result = mappedExamModel.toExam()
        assertThat(result).isNull()
    }

    @Test
    fun `mapped to standard exam model with empty level property returns error`() {
        val mappedExamModel = MappedExamModel(
            id = 0,
            title = "Quiz 1",
            field = "field a",
            image = "empty image",
            questions = listOf(DefaultImpl.questionModel),
            level = "",
            limitPoint = 80.0,
            isActive = true
        )

        val result = mappedExamModel.toExam()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with non-correct questions returns error`() {
        val examModel = ExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = "Qualification a",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "true"
        )

        val result = examModel.toMappedExam()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with non-correct isActive property returns error`() {
        val exam = ExamModel(
            id = 0,
            title = "Quiz 1",
            field = "Android",
            image = "empty image",
            questions = "Qualification a",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "empty"
        )

        val result = exam.toMappedExam()

        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with empty title property returns error`(){
        val exam = ExamModel(
            id = 0,
            title = "",
            field = "Android",
            image = "empty image",
            questions = "Qualification a",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "empty"
        )

        val result = exam.toMappedExam()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with empty field property returns error`(){
        val exam = ExamModel(
            id = 0,
            title = "Quiz a",
            field = "",
            image = "empty image",
            questions = "Qualification a",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "empty"
        )

        val result = exam.toMappedExam()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with empty image property returns error`(){
        val exam = ExamModel(
            id = 0,
            title = "Quiz a",
            field = "field 1",
            image = "",
            questions = "Qualification a",
            level = "Advanced",
            limitPoint = 80.0,
            isActive = "empty"
        )

        val result = exam.toMappedExam()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped exam model with empty level property returns error`(){
        val exam = ExamModel(
            id = 0,
            title = "Quiz a",
            field = "field 1",
            image = "image empty",
            questions = "Qualification a",
            level = "",
            limitPoint = 80.0,
            isActive = "empty"
        )

        val result = exam.toMappedExam()
        assertThat(result).isNull()
    }
}