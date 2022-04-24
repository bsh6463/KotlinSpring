package com.example.kotlinspring.model


import com.example.kotlinspring.annotaion.StringFormatDateTime
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

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴일 올바르지 않습니다.")
    var createdAt: String? = null //yyyy-MM-dd HH:mm:ss
)