package com.drore.cloud.service;

import com.drore.cloud.entity.ComparisonData;
import com.drore.cloud.entity.SyncData;

import java.util.List;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public interface SyncService {
    List comparison(ComparisonData comparisonData);

    List sync(List<SyncData> syncDatas);
}
