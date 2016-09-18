package com.drore.cloud.web;

import com.drore.cloud.entity.ComparisonData;
import com.drore.cloud.entity.SyncData;
import com.drore.cloud.service.SyncService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Controller
public class SyncController {
    @Resource
    private SyncService syncService;

    @ResponseBody
    @RequestMapping("/sync/comparison")
    public List comparison(@RequestBody ComparisonData comparisonData) {
        return syncService.comparison(comparisonData);
    }

    @ResponseBody

    @RequestMapping("/sync/sync")
    public List sync(@RequestBody List<SyncData> syncDatas) {
        return syncService.sync(syncDatas);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
