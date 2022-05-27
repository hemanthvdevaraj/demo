package com.play.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.play.demo.entity.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

}
