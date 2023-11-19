package com.natiqhaciyef.util.common.util.objects

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import com.natiqhaciyef.util.common.util.helpers.getNow
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.LearnSectionModel
import com.natiqhaciyef.util.models.QuestionModel
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.enums.ExamLevels
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.enums.UserTypes
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel


object DefaultImpl {

    val lsm = LearnSectionModel(
        title = "Interview practice",
        icon = Icons.Default.MenuBook,
        image = "https://scontent.fgyd21-1.fna.fbcdn.net/v/t39.30808-6/375754160_701337858701666_6160760067267384420_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=5f2048&_nc_ohc=YP669cQYXnoAX-5Ii5u&_nc_ht=scontent.fgyd21-1.fna&oh=00_AfDhldc_R0C4kHfQENVrzVJWopjCcl0GuFDV8z2SYov4Yw&oe=653BF48B"
    )

    val user = UserModel(
        id = 0,
        name = "Nathan",
        email = "nathan@gmail.com",
        password = "Nathan123"
    )

    val mappedPost = MappedPostModel(
        id = 0,
        title = "Adopt Compose for View based libraries",
        description = "Jetpack Compose is designed with View interoperability in mind. This means that existing View-based libraries can readily be used in Compose. However, considering how View-based libraries are used in Compose can improve support for Compose. For library authors, this can inform how you design your APIs, and further, how you may want to provide explicit support for Compose through additional libraries.",
        image = "https://miro.medium.com/v2/resize:fit:1100/format:webp/1*lQAfLPEsevYZmnYDBSqylQ.png",
        likeCount = 5,
        location = "null",
        publishDate = "18.10.2023 13:21",
        user = UserWithoutPasswordModel(
            id = 0,
            name = "Google",
            email = "google.com",
        )
    )

    val mappedExamModel = MappedExamModel(
        id = 0,
        title = "Android Development - 1",
        field = "${QuestionCategories.ANDROID.title} ${QuestionCategories.KOTLIN.title}",
        image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fandroid.png?alt=media&token=988fb099-d2fa-43c8-8db3-c11620f1b61c",
        questions = listOf(
            QuestionModel(
                title = "Which is correct constant value declaration in Kotlin ?",
                variants = listOf(
                    "const f = 13",
                    "const val n = 11",
                    "var a = 10",
                    "val x = 12"
                ),
                correctVariant = "const val n = 11",
                topic = QuestionCategories.KOTLIN.title,
                point = 10.0
            ),
            QuestionModel(
                title = "Which is not the layer of Android OS ?",
                variants = listOf("Libraries", "Kernel", "Applications", "OnCreate"),
                correctVariant = "OnCreate",
                topic = QuestionCategories.ANDROID.title,
                point = 15.0
            ),
            QuestionModel(
                title = "What is coroutine in Kotlin ?",
                variants = listOf(
                    "Coroutines allow the only synchronous execution of a block of code to be suspended and then resumed later, so that other work can be done in the meantime.",
                    "Coroutines allow the asynchronous execution of a block of code to be suspended and then resumed later, so that other work can be done in the meantime.",
                    "Coroutines is a part of hardware which allows to execute code lines. It support to generate execution after compile",
                    "Coroutines is a part of software which allows to generate compiled codes. It support to compile all software background codes."
                ),
                correctVariant = "Coroutines allow the asynchronous execution of a block of code to be suspended and then resumed later, so that other work can be done in the meantime.",
                topic = QuestionCategories.KOTLIN.title,
                point = 15.0
            ),
            QuestionModel(
                title = "What is enum class in kotlin ?",
                variants = listOf(
                    "It saves all variables in local storage",
                    "It uses only single instance of this class.",
                    "It holds constant, same type and categorized data which usually used for id",
                    "It requires primary constructor with minimum 1 parameter in it"
                ),
                correctVariant = "It holds constant, same type and categorized data which usually used for id",
                topic = QuestionCategories.KOTLIN.title,
                point = 15.0
            ),
            QuestionModel(
                title = "Which is commonly using for create Android UI ?",
                variants = listOf(
                    "Navigation",
                    "Fragment",
                    "Context",
                    "LinearLayout"
                ),
                correctVariant = "Fragment",
                topic = QuestionCategories.ANDROID.title,
                point = 15.0
            ),
            QuestionModel(
                title = "Which is correct lifecycle of Activity ?",
                variants = listOf(
                    "onPrint",
                    "onView",
                    "onStart",
                    "onViewCreated"
                ),
                correctVariant = "onStart",
                topic = QuestionCategories.ANDROID.title,
                point = 15.0
            ),
            QuestionModel(
                title = "Which is not the type of Coroutine Context (Dispatcher) ?",
                variants = listOf(
                    "IO",
                    "Major",
                    "Main",
                    "Default"
                ),
                correctVariant = "Major",
                topic = QuestionCategories.KOTLIN.title,
                point = 15.0
            ),
        ),
        limitPoint = 70.0,
        level = ExamLevels.BEGINNER.title,
        isActive = true
    )

    val questionModel = QuestionModel("Question 1", listOf("A", "B", "C", "D"), "A", "Letter", 3.0)

    val contactModel = ContactModel(
        id = 0,
        name = "Nothing",
        email = "nothing@gmail.com",
        phone = "+944 00330033",
        description = "nothing descripted",
        field = "nothing",
    )

    val interviewQuestionModel = InterviewQuestionModel(
        id = 0,
        title = "title 1",
        description = "description 1",
        solution = "solution 1",
        image = "image not found",
        level = "level 1",
        origin = "origin not found",
        field = "field 1",
        subfield = "sub-field 1",
        date = "date 1"
    )

    val verifiedUserModel = VerifiedUserModel(
        id = 0,
        name = "Nathan",
        email = "nathan123@gmail.com",
        phone = "+994 99 555 5665",
        image = "empty image",
        idImage = "empty id image",
        password = "Nathan123",
        type = UserTypes.PreVerifiedUser.name
    )
}