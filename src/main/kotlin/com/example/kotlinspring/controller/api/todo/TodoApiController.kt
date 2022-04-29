package com.example.kotlinspring.controller.api.todo

import com.example.kotlinspring.model.http.TodoDto
import com.example.kotlinspring.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController(val todoService: TodoService) {

    @GetMapping("/")
    fun read(@RequestParam(required = false) index:Int): ResponseEntity<Any> {

        return index?.let {
            todoService.read(index)
        }.let {
            return ResponseEntity.ok(it)
        }?: kotlin.run {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo").build()
        }

    }
    @GetMapping("/all")
    fun readAll(): MutableList<TodoDto> {

        return todoService.readAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody todoDto:TodoDto): ResponseEntity<TodoDto?>? {
        return ResponseEntity.status(200).body(todoService.create(todoDto))
    }

    @PutMapping
    fun update(@Valid @RequestBody todoDto:TodoDto): TodoDto? {
        return todoService.update(todoDto)
    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index:Int): ResponseEntity<Any>? {
       if(!todoService.delete(_index)){
           return ResponseEntity.status(500).build()
       }

        return ResponseEntity.ok().build()
    }
}