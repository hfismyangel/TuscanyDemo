package com.jnshu.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnshu.pojo.Developer;
import com.jnshu.service.CacheUtil;
import com.jnshu.service.DeveloperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jnshu.dao.DeveloperDAO;
import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {
    List<Developer> developers;
    private static final Logger logger = Logger.getLogger(DeveloperServiceImpl.class);
    @Autowired
    private DeveloperDAO DeveloperDAO;
    @Autowired
    @Qualifier("cacheUtilImpl")
    private CacheUtil cacheUtil;

    public List<Developer> queryDeveloper() {
        String json = cacheUtil.get("DeveloperList");
        if (json != null) {
            logger.debug("从缓存中读取开发者列表---------------------json为："+json);
            List<Developer> developersCache = new Gson().fromJson(cacheUtil.get("DeveloperList"),new TypeToken<List<Developer
                    >>(){}.getType());
//            模拟缓存击穿
//            developers.remove(1);
//            logger.debug("-----------------击穿缓存："+developers.toString());
//            if(developers.size()<6){
//                developers = DeveloperDAO.queryDeveloper("%");
//                return developers;
//            }
            return developersCache;
        } else {
            developers = DeveloperDAO.queryDeveloper("%");
            cacheUtil.put("DeveloperList", developers);
            logger.debug("--------------将开发者列表添加到redis缓存中");
            return developers;
        }
    }
}
