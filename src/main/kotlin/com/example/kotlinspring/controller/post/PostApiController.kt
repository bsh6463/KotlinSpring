package com.example.kotlinspring.controller.post

import com.example.kotlinspring.model.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String{
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "post-mapping2"
    }

    //json <-> object: object Mapper, 스프링에서 기본적으로 사용
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }



}