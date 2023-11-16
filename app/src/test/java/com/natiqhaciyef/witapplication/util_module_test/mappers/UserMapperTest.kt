package com.natiqhaciyef.witapplication.util_module_test.mappers

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.mappers.mapToWithoutPassword
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import org.junit.Test

class UserMapperTest {

    @Test
    fun `standard to mapped user returns success`(){
        val user = UserModel(
            id = 0,
            name = "Arslan",
            email = "arslan123@gmail.com",
            password = "arslan123"
        )

        val mappedUser = UserWithoutPasswordModel(
            id = 0,
            name = "Arslan",
            email = "arslan123@gmail.com"
        )

        val result = user.mapToWithoutPassword()
        assertThat(result).isEqualTo(mappedUser)
    }

    @Test
    fun `standard to mapped user without name returns error`(){
        val user = UserModel(
            id = 0,
            name = "",
            email = "arslan123@gmail.com",
            password = "arslan123"
        )

        val result = user.mapToWithoutPassword()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped user without email returns error`(){
        val user = UserModel(
            id = 0,
            name = "Arslan",
            email = "",
            password = "arslan123"
        )

        val result = user.mapToWithoutPassword()
        assertThat(result).isNull()
    }

    @Test
    fun `standard to mapped user without password returns error`(){
        val user = UserModel(
            id = 0,
            name = "Arslan",
            email = "arslan123@gmail.com",
            password = ""
        )

        val result = user.mapToWithoutPassword()
        assertThat(result).isNull()
    }
}