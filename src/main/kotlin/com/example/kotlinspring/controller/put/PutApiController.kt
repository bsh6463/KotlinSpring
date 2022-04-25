package com.example.kotlinspring.controller.put

import com.example.kotlinspring.model.http.Result
import com.example.kotlinspring.model.http.UserRequest
import com.example.kotlinspring.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
import javax.validation.Valid

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
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String>? {

        if (bindingResult.hasErrors()){
            // 500 err
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field.toString() + " : " + message +"\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }
        return ResponseEntity.ok("")
        // 0. userResponse
//        return  UserResponse().apply {
//            // 1. result
//            this.result =  Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//        }.apply {
//            // 2. description
//            this.description = "~~~~~~~"
//        }.apply {
//            // 3. user mutable list
//            val userList = mutableListOf<UserRequest>()
//
//            userList.add(userRequest)
//
//            userList.add(UserRequest().apply {
//                this.name = "A"
//                this.age = 10
//                this.email = "A.com"
//                this.address = "a address"
//                this.phoneNumber = "01011111"
//            })
//
//            userList.add(UserRequest().apply {
//                this.name = "B"
//                this.age = 20
//                this.email = "B.com"
//                this.address = "b address"
//                this.phoneNumber = "01011111"
//            })
//
//           this.user = userList
//        }
    }
}