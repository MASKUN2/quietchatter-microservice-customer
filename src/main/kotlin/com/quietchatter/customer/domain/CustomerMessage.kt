package com.quietchatter.customer.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "customer_message")
class CustomerMessage(
    @Column(name = "message", columnDefinition = "TEXT")
    var message: String
) : BaseEntity() {
    fun update(message: String) {
        this.message = message
    }
}
