package com.quietchatter.customer.application.service

import com.quietchatter.customer.application.out.CustomerMessageRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CustomerMessageServiceTest {

    private val repository: CustomerMessageRepository = mock()
    private val service = CustomerMessageService(repository)

    @Test
    fun `should save message`() {
        val message = "Hello, Customer!"
        
        service.create(message)
        
        verify(repository).save(any())
    }
}
