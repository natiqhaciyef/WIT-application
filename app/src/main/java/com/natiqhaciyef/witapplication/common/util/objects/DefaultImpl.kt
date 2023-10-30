package com.natiqhaciyef.witapplication.common.util.objects

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import com.natiqhaciyef.witapplication.data.models.LearnSectionModel
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.UserWithoutPasswordModel
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel


object DefaultImpl {

    val lsm = LearnSectionModel(
        title = "Interview practice",
        icon = Icons.Default.MenuBook,
        image = "https://scontent.fgyd21-1.fna.fbcdn.net/v/t39.30808-6/375754160_701337858701666_6160760067267384420_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=5f2048&_nc_ohc=YP669cQYXnoAX-5Ii5u&_nc_ht=scontent.fgyd21-1.fna&oh=00_AfDhldc_R0C4kHfQENVrzVJWopjCcl0GuFDV8z2SYov4Yw&oe=653BF48B"
    )

    val post = MappedPostModel(
        id = 0,
        title = "Adopt Compose for View based libraries",
        description = "Jetpack Compose is designed with View interoperability in mind. This means that existing View-based libraries can readily be used in Compose. However, considering how View-based libraries are used in Compose can improve support for Compose. For library authors, this can inform how you design your APIs, and further, how you may want to provide explicit support for Compose through additional libraries.",
        image = "https://miro.medium.com/v2/resize:fit:1100/format:webp/1*lQAfLPEsevYZmnYDBSqylQ.png",
        likeCount = 5,
        location = null,
        publishDate = "18.10.2023 13:21",
        user = UserWithoutPasswordModel(
            id = 0,
            name = "Google",
            email = "google.com",
        )
    )
}