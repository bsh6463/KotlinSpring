package com.example.kotlinspring.controller.response

import com.example.kotlinspring.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    //1. get, 4xx
    @GetMapping()
    fun getMapping(@RequestParam age:Int?): ResponseEntity<String>? {

        return age?.let {
            //age != null
            if(it < 20){
                return ResponseEntity.badRequest().body("age 값은 20이상이어야 합니다.")
            }

            ResponseEntity.ok("ok")
        }?: kotlin.run {
            //age == null
            return ResponseEntity.status(400).body("age값이 누락되었습니다.")
        }


        /*
        //required = 필수 값 옵션, default: trie
        //1. age == null -> 400
        if(age == null){
            //return ResponseEntity.badRequest().body("fail")
            return ResponseEntity.status(400).body("age값이 누락되었습니다.")
        }

        //2. age < 20 -> 400
        if (age < 20){
            return ResponseEntity.badRequest().body("age 값은 20이상이어야 합니다.")
        }

        return ResponseEntity.ok("ok")
        */
    }

    //2. post, 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any>? {
        return ResponseEntity.status(200).body(userRequest) //body 비어있음.
    }

    //3. put, 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        //기존 데이터가 없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    //4. delete, 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }
}