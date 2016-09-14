package com.drore.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.entity.ComparisonData;
import com.drore.cloud.entity.Param;
import com.drore.cloud.entity.SyncData;
import com.drore.cloud.service.SyncService;
import com.drore.cloud.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Service
public class SyncServiceImpl implements SyncService {
    Logger logger = Logger.getLogger(SyncServiceImpl.class);

    @Value("${url}")
    private String url;
    @Value("${comparison}")
    private String comparison;
    @Resource
    private Param param;


    @Override
    public List comparison(ComparisonData comparisonData) {
        param.setData(comparisonData);
        if (logger.isDebugEnabled()) {
            logger.debug(JSON.toJSONString(param));
        }
        String comparison_url = url + comparison;

        String response = HttpUtils.postJson(comparison_url, JSON.parseObject(JSON.toJSONString(param)));
        if (!StringUtils.isEmpty(response)) {
            if (logger.isDebugEnabled()) {
                logger.debug(response);
            }
            Optional<JSONObject> data = Optional.ofNullable(JSON.parseObject(response));
            if (data.isPresent()) {
                int errcode = data.get().getIntValue("errCode");
                if (errcode == 8200) {
                    String value = data.get().getString("value");
                    return JSONArray.parseArray(value);
                }
            }
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List sync(SyncData syncData) {
        return null;
    }
}
