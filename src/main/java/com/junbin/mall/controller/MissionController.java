package com.junbin.mall.controller;

import com.junbin.mall.dto.MissionDto;
import com.junbin.mall.dto.UserCouponDto;
import com.junbin.mall.service.UserMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/mission")
@Api(tags = "用户活动接口" )
public class MissionController {

    private final UserMissionService userMissionService;

    public MissionController(UserMissionService userMissionService) {
        this.userMissionService = userMissionService;
    }

    @GetMapping("/{missionId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取活动信息")
    public MissionDto getMission(@PathVariable Long missionId) {
        return userMissionService.getMission(missionId);
    }
}
