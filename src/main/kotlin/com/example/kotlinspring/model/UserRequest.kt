package com.example.kotlinspring.model


import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(


    @field:NotEmpty
    @field:Size(min=2, max=8)
    var name: String? = null,

    @field:PositiveOrZero
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String? = null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") //정규식
    var phoneNumber: String? = null, //phone_number: snake case

    var createdAt: String? = null //yyyy-MM-dd HH:mm:ss
){
    @AssertTrue(message = "생성 일자의 패턴은 yyyy-MM-dd HH:mm:ss여야 합니다.") //validation할 때 해당 메서드 실행됨.
    private fun isValidCreatedAt(): Boolean{

        return try {
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e:Exception){
            false
        }
    }
}