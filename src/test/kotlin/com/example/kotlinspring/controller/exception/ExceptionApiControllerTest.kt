package com.example.kotlinspring.controller.exception

import com.example.kotlinspring.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
internal class ExceptionApiControllerTest {

    //가상 요청 만들기
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest() {
        val queryParameters = LinkedMultiValueMap<String, String>()
        queryParameters.add("name", "abc")
        queryParameters.add("age", "20")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParameters)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("abc 20")
        )
    }

    @Test
    fun getTestFail() {
        val queryParameters = LinkedMultiValueMap<String, String>()
        queryParameters.add("name", "abc")
        queryParameters.add("age", "5")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParameters)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("5")
        )
    }



    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "abc"
            this.age = 10
            this.phoneNumber = "010-1111-1111"
            this.address = "서울"
            this.email = "aaa@naver.co"
            this.createdAt = "2020-01-01 10:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.name").value("abc")
        )

    }

    @Test
    fun postTestFail() {
        val userRequest = UserRequest().apply {
            this.name = "abc"
            this.age = -1
            this.phoneNumber = "010-1111-1111"
            this.address = "서울"
            this.email = "aaa@naver.co"
            this.createdAt = "2020-01-01 10:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        )

    }
}