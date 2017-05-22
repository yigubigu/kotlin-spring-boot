package com.alct.restful.controller

import com.alct.restful.biz.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * * 注意:
 *      这里要使用@Controller注解
 *      而不要使用@RestController
 *      否则return "index";只是返回字符串"index"， 不能跳转到index.html
 *
 *      @RestController is a stereotype annotation that combines @ResponseBody and @Controller.
 *      @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 */


@Controller
class CustomerController(val customerService: CustomerService) {

    @GetMapping(value = "/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/customers")
    fun listAll(model: Model): String {
        val allCustomers = customerService.findAll()
        model.addAttribute("customers", allCustomers)
        return "customers"
    }

    @GetMapping("/listCustomers")
    @ResponseBody
    fun listCustomers(model: Model) = customerService.findAll()

    @GetMapping("/{lastName}")
    @ResponseBody
    fun findByLastName(@PathVariable lastName: String)
            = customerService.findByLastName(lastName)
}
