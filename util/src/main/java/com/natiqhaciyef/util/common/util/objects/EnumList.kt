package com.natiqhaciyef.util.common.util.objects

import android.content.Context
import com.natiqhaciyef.util.R
import com.natiqhaciyef.util.models.LearnSectionModel
import com.natiqhaciyef.util.models.QuestionModel
import com.natiqhaciyef.util.models.enums.ExamLevels
import com.natiqhaciyef.util.models.enums.Season
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.enums.QuestionLevels
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.util.models.service.InfoModel
import com.natiqhaciyef.util.models.service.LanguageModel

object EnumList {
    val seasonsList = listOf(
        Season.Winter.name,
        Season.Spring.name,
        Season.Summer.name,
        Season.Autumn.name,
    )

    val fields = listOf(
        LearnSectionModel(
            title = "General programming",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fgeneral_programming.png?alt=media&token=5c3a9f7d-4b70-4228-b788-f392500cb346&_gl=1*z8nn89*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3ODAzLjQ3LjAuMA.."
        ),
        LearnSectionModel(
            title = "AI",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fai_engineering.png?alt=media&token=335e2479-5260-4eb3-b81b-d6a029bb69ae&_gl=1*h4wmcm*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NTk0LjM2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Mobile",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fmobile.png?alt=media&token=3070300a-bac2-49b3-a6b8-b7f4f5c5bb60&_gl=1*mffr83*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjY4LjI2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Frontend",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Ffrontend.png?alt=media&token=4f1a549e-5fc9-41ea-b25f-c24eb8360dfc&_gl=1*18g8az7*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjU2LjM4LjAuMA.."
        ),
        LearnSectionModel(
            title = "Backend",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fbackend.png?alt=media&token=fb8bb05c-7a2f-42c5-9cd1-9a7b98eacf0c&_gl=1*2kc2su*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjE0LjE2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Cyber Security",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fcybersecurity.png?alt=media&token=a7b70934-57cb-4d0c-8bcb-e769a8ed763e&_gl=1*151puly*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjM0LjYwLjAuMA.."
        )
    )


    val languages = listOf(
        LanguageModel(title = "az", "Azerbaijani", false),
        LanguageModel(title = "tr", "Turkish", false),
        LanguageModel(title = "", "English", false),
    )


    fun getAllHelpInformation(context: Context) = listOf(
        InfoModel(
            title = context.getString(R.string.how_to_verify),
            description = context.getString(R.string.how_to_verify_description)
        ),
        InfoModel(
            title = context.getString(R.string.how_to_learn_from_mentor),
            description = context.getString(R.string.how_to_learn_from_mentor_description)
        ),
        InfoModel(
            title = context.getString(R.string.how_to_become_partner),
            description = context.getString(R.string.how_to_become_partner_description)
        ),
        InfoModel(
            title = context.getString(R.string.how_to_configure_custom_events),
            description = context.getString(R.string.how_to_configure_custom_events_description)
        ),
        InfoModel(
            title = context.getString(R.string.how_to_ticket_system_works_for_events),
            description = context.getString(R.string.how_to_ticket_system_works_for_events_description)
        ),
        InfoModel(
            title = context.getString(R.string.other),
            description = ""
        )
    )


    val questionCategoriesProgrammingLanguages = listOf(
        QuestionCategories.JAVA.title,
        QuestionCategories.JAVASCRIPT.title,
        QuestionCategories.CPP.title,
        QuestionCategories.PYTHON.title,
        QuestionCategories.KOTLIN.title,
        QuestionCategories.CSHARP.title,
        QuestionCategories.DART.title,
        QuestionCategories.GOLANG.title,
        QuestionCategories.REACTJS.title,
        QuestionCategories.NODEJS.title,
    )

    val questionCategoriesFields = listOf(
        QuestionCategories.ANDROID.title,
        QuestionCategories.IOS.title,
        QuestionCategories.CROSS_PLATFORM_MOBILE.title,
        QuestionCategories.BACKEND.title,
        QuestionCategories.FRONTEND.title,
        QuestionCategories.AI.title,
        QuestionCategories.ML.title,
        QuestionCategories.DB.title,
        QuestionCategories.CYBERSECURITY.title,
    )

    val questionCategoriesLevels = listOf(
        QuestionLevels.ALL.title,
        QuestionLevels.EASY.title,
        QuestionLevels.MEDIUM.title,
        QuestionLevels.HARD.title,
        QuestionLevels.EXPERT.title,
    )

    val examModel = MappedExamModel(
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
}