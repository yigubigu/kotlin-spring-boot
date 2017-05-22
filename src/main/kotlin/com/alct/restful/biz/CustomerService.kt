package com.alct.restful.biz

import com.alct.restful.entity.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerService : CrudRepository<com.alct.restful.entity.Customer, Long> {

    fun findByLastName(lastName: String): Iterable<com.alct.restful.entity.Customer>
}
