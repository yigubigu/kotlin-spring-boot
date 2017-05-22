package com.alct.restful.controller

import org.springframework.stereotype.Controller
import com.alct.devicelocationcollection.service.DeviceMapperService;
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import com.alct.util.DateUtil;
import java.util.*

/**
 * Created by lory on 2017/5/22.
 */
@RequestMapping("/mappers")
@Controller
class DeviceMapperController2(val deviceMapperService: DeviceMapperService) {

    @GetMapping("/deviceMappers")
    @ResponseBody
    fun listDeviceAMappers(@RequestHeader(value = "access-token")  accessToken: String, @RequestParam licensePlate: String,
                           @RequestParam(required = false) @DateTimeFormat(pattern = DateUtil.pattern) from: Date,
                           @RequestParam(required = false) @DateTimeFormat(pattern = DateUtil.pattern) to : Date,
                           @PageableDefault( direction = Direction.DESC)  pageable: Pageable) =
            deviceMapperService.getDeviceMapper(accessToken, licensePlate);

    @GetMapping("/listDeviceMappers")
    fun listAll(model: Model): String {
        var accessToken = "IyVdApUiclfvKdougvyuaPdYoIaUEJZy";
        var licensePlate = "粤BBZ753";
        val mobileTruckMapperDto = deviceMapperService.getDeviceMapper(accessToken, licensePlate);
        var mobileTruckMapperDtos = arrayOf(mobileTruckMapperDto);
        model.addAttribute("mobileTruckMapperDtos", mobileTruckMapperDtos);
        return "deviceMappers"
    }
}