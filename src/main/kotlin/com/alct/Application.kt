package com.alct

import com.alct.restful.biz.CustomerService
import com.alct.restful.entity.Customer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import java.util.*

@SpringBootApplication
class Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(repository: com.alct.restful.biz.CustomerService) = CommandLineRunner {
        // save a couple of customers
        val now = Date();
        repository.save(com.alct.restful.entity.Customer("Jason", "Chen", now, now, 0, now))
        repository.save(com.alct.restful.entity.Customer("Bluce", "Li", now, now, 0, now))
        repository.save(com.alct.restful.entity.Customer("Michelle", "Chen", now, now, 0, now))

        for (customer in repository.findAll()) {
            log.info(customer.toString())
        }

        // fetch an individual customer by ID
        val customer = repository.findOne(1L)
        log.info(customer.toString())
        // fetch customers by last name
        for (bauer in repository.findByLastName("Chen")) {
            log.info(bauer.toString())
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
