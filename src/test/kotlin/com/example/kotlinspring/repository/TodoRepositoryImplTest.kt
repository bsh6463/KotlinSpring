package com.example.kotlinspring.repository

import com.example.kotlinspring.config.AppConfig
import com.example.kotlinspring.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

//test시 로드 할 클래스 지정.
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
internal class TodoRepositoryImplTest{

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "test"

            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
    }

    @Test
    fun saveAllTest(){
        val todoList= mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "test1"

                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "test2"

                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "test3"

                this.schedule = LocalDateTime.now()
            }
        )

        val result =  todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest(){
        val todoList= mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "test1"

                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "test2"

                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "test3"

                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(2)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun updateTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "test"

            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = 1
            this.title = "업데이트 일정"
            this.description = "업데이트 테스트"

            this.schedule = LocalDateTime.now()
        }
        val result = todoRepositoryImpl.save(newTodo)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(insertTodo?.index, result?.index)
        assertEquals("업데이트 일정", result?.title)
    }
}