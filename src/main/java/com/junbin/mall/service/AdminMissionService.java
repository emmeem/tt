package com.junbin.mall.service;

import com.junbin.mall.domain.Mission;
import com.junbin.mall.dto.ActivityEffectDto;
import com.junbin.mall.dto.AdminMissionDto;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.MissionIsExistException;
import com.junbin.mall.repository.MissionRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminMissionService {
    private final MissionRepository missionRepository;

    private final UserRepository userRepository;

    public AdminMissionService(MissionRepository missionRepository,
    UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AdminMissionDto setMission(AdminMissionDto adminMissionDto) {
        Optional<Mission> mission = missionRepository.findById(adminMissionDto.getId());
        if(mission.isPresent()) {
            throw new MissionIsExistException(ExceptionMessage.MISSION_IS_EXIST);
        }
        Mission newMission =missionRepository.save(ConvertTool.convertObject(adminMissionDto, Mission.class));
        return ConvertTool.convertObject(newMission, AdminMissionDto.class);
    }

    private ActivityEffectDto getActivityEffectByCompany(String companyName) {

            ActivityEffectDto activityEffectDto = ActivityEffectDto.builder()
                    .numsOfKnowActivity(userRepository.countUserById())
                    .numsOfJoinActivity(userRepository.countUserById())
                    .numsOfGetCoupon(userRepository.countUserById())
                    .build();
            return  activityEffectDto;


    }


    public ActivityEffectDto getActivityEffect(String companyName) {
        ActivityEffectDto activityEffectDto = getActivityEffectByCompany(companyName);

        return activityEffectDto;
    }

}
