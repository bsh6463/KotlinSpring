package com.example.kotlinspring.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import javax.validation.Validation

internal class TodoDtoTest{

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply {
            this.title = "dd"
            this.description = ""
            this.schedule = "2020-01-01 10:01:01"
        }
        val result = validator.validate(todoDto)

        result.forEach {
            println(it.propertyPath.last().name)
            println(it.message)
        }

        Assertions.assertEquals(true, result.isEmpty())
    }
}