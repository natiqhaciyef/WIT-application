package com.natiqhaciyef.util.common.util.objects

import android.content.Context
import com.natiqhaciyef.util.R
import com.natiqhaciyef.util.models.LearnSectionModel
import com.natiqhaciyef.util.models.enums.Season
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.enums.QuestionLevels
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

    fun getSelectedLanguage(lang: String) = when (lang) {
        "az" -> LanguageModel(title = "az", "Azerbaijani", false)
        "en" -> LanguageModel(title = "en", "English", false)
        "tr" -> LanguageModel(title = "tr", "Turkish", false)
        else -> LanguageModel(title = "", "English", false)
    }


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
        QuestionCategories.MOBILE.title,
        QuestionCategories.GENERAL_PROGRAMMING.title,
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
}