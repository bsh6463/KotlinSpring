package com.example.kotlinspring.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated //얘를 적용해야 하위에 있는 validation Annotation이 동작함.
class DeleteApiController {
    // 1. path variable

    // 2. request param

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name")
        @NotNull
        @Size(min=2, max=5)
        _name: String,

        @NotNull(message = "age값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야합니다.")
        @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)

        return _name+" "+_age
    }

    @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
    fun deleteMappingPath(
        @PathVariable(value = "name")
        @NotNull
        @Size(min=2, max=5)
        _name: String,

        @NotNull(message = "age값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야합니다.")
        @PathVariable(name = "age") _age:Int
    ): String {
        println(_name)
        println(_age)

        return _name+" "+_age
    }
}