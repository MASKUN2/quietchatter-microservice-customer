package com.quietchatter.customer.adaptor.`in`.web

import com.quietchatter.customer.application.`in`.CustomerMessageCreatable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/customer")
class CustomerMessageController(
    private val customerMessageCreatable: CustomerMessageCreatable
) {
    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMessage(@RequestBody request: CreateMessageRequest) {
        customerMessageCreatable.create(request.message)
    }

    data class CreateMessageRequest(val message: String)
}
