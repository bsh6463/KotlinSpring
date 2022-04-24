package com.example.kotlinspring.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse (
    var result: Result? = null,
    var description: String? = null,
    @JsonProperty(value = "user")
    var userRequest: MutableList<UserRequest>? = null

)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
    var resultCode : String? = null,
    var resultMessage : String? = null
)


/*
{
    "result" : {
     "result_code" : "OK",
    "result_mesage" : "성공"
    },
    "description" : "~~~",
    "user" : [
        {"name" : "aaa",
        "age": 1,
        "email":"aa",
        "phoneNumber" : "111"
        },
        {"name" : "bbb",
        "age": 2,
        "email":"bb",
        "phoneNumber" : "222"
        },
        {"name" : "ccc",
        "age": 3,
        "email":"cc" ,
        "phoneNumber" : "333"
        }
    ]
}
 */