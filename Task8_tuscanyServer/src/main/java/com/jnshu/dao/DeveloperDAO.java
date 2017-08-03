package com.jnshu.dao;

import com.jnshu.pojo.Developer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
@Repository
public interface DeveloperDAO {
    List<Developer> queryDeveloper(String id);
}
