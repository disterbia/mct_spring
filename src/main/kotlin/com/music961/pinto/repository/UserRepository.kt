package com.music961.pinto.repository

import com.music961.pinto.model.User
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface UserRepository {
    fun getUserById(id : Int) : User
    fun loginGoogleOauth(email : String) : User
    fun joinByGoogle(
            name  : String,
            email : String
    )
}