package com.junbin.mall.controller;

import com.junbin.mall.dto.ActivityEffectDto;
import com.junbin.mall.dto.AdminMissionDto;
import com.junbin.mall.service.AdminMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin/mission")
@Api(tags = "管理员任务管理接口")
public class AdminMissionController {

    private final AdminMissionService adminMissionService;

    public AdminMissionController(AdminMissionService adminMissionService) {
        this.adminMissionService = adminMissionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "设置任务",httpMethod = "POST")
    public AdminMissionDto setMission(@RequestBody AdminMissionDto adminMissionDto) {
        return adminMissionService.setMission(adminMissionDto);
    }

    @GetMapping("/effect/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取活动效果", httpMethod = "GET")
    public ActivityEffectDto getActivityEffect(@PathVariable String companyName) {
        return adminMissionService.getActivityEffect(companyName);
    }



}
