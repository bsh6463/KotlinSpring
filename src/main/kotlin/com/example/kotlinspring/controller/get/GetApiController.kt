package com.example.kotlinspring.controller.get

import com.example.kotlinspring.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String{
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET] , path = ["/request-mapping"]) //http method에 관계없이.
    fun requestMapping(): String{
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String{
        println("name: ${name}, age: ${age}")
        return name + " " + age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String{
        val name = "kotlin"
        println("name: ${_name}, age: ${age}")
        return _name + " " + age
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(@RequestParam name: String, @RequestParam age: Int): String{
        println("name: ${name}, age: ${age}")
        return "$name $age"
    }

    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(@ModelAttribute userRequest: UserRequest): UserRequest {
        println(userRequest.toString())
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        return map
    }
}