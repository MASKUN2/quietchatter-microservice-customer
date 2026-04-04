package com.quietchatter.customer.adaptor.out.persistence

import com.quietchatter.customer.application.out.CustomerMessageRepository
import com.quietchatter.customer.domain.CustomerMessage
import org.springframework.stereotype.Component

@Component
class CustomerMessagePersistenceAdapter(
    private val jpaRepository: CustomerMessageJpaRepository
) : CustomerMessageRepository {
    override fun save(customerMessage: CustomerMessage): CustomerMessage {
        return jpaRepository.save(customerMessage)
    }
}
