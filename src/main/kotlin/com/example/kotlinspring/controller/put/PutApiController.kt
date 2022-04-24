package com.example.kotlinspring.controller.put

import com.example.kotlinspring.model.Result
import com.example.kotlinspring.model.UserRequest
import com.example.kotlinspring.model.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String{

        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {

        return "request-mapping -put method"
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. userResponse
       return  UserResponse().apply {
            // 1. result
            this.result =  Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "A"
                this.age = 10
                this.email = "A.com"
                this.address = "a address"
                this.phoneNumber = "01011111"
            })

            userList.add(UserRequest().apply {
                this.name = "B"
                this.age = 20
                this.email = "B.com"
                this.address = "b address"
                this.phoneNumber = "01011111"
            })

           this.userRequest = userList
        }
    }
}