package com.example.kotlinspring.validator

import com.example.kotlinspring.annotaion.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDateTimeValidator: ConstraintValidator<StringFormatDateTime, String> {

    private var pattern : String? = null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    //정상이면 true
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        return try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        }catch (e:Exception){
            false
        }
    }
}