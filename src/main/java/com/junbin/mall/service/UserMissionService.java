package com.junbin.mall.service;

import com.junbin.mall.domain.Mission;
import com.junbin.mall.dto.MissionDto;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.MissionIsExistException;
import com.junbin.mall.repository.MissionRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

@Service
public class UserMissionService {

    private final MissionRepository missionRepository;

    public UserMissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public MissionDto getMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionIsExistException(ExceptionMessage.MISSION_IS_NOT_EXIST));

        return ConvertTool.convertObject(mission, MissionDto.class);
    }
}
