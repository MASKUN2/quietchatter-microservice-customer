package com.quietchatter.customer.adaptor.out.persistence

import com.quietchatter.customer.domain.CustomerMessage
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerMessageJpaRepository : JpaRepository<CustomerMessage, Long>
