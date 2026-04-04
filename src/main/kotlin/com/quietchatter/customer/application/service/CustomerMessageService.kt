package com.quietchatter.customer.application.service

import com.quietchatter.customer.application.`in`.CustomerMessageCreatable
import com.quietchatter.customer.application.out.CustomerMessageRepository
import com.quietchatter.customer.domain.CustomerMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerMessageService(
    private val customerMessageRepository: CustomerMessageRepository
) : CustomerMessageCreatable {

    @Transactional
    override fun create(message: String) {
        val customerMessage = CustomerMessage(message)
        customerMessageRepository.save(customerMessage)
    }
}
