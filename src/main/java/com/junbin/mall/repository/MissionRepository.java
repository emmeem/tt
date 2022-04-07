package com.junbin.mall.repository;

import com.junbin.mall.domain.Mission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {

}
