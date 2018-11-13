package com.ysc.myspringboot.dao;

import com.ysc.myspringboot.entity.Boy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoyDAO extends JpaRepository<Boy, Integer> {

}
