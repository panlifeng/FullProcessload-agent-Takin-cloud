package io.shulie.takin.cloud.open.api.impl.statistics;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.TypeReference;

import org.springframework.stereotype.Service;

import io.shulie.takin.common.beans.response.ResponseResult;
import io.shulie.takin.cloud.open.constant.CloudApiConstant;
import io.shulie.takin.cloud.open.req.statistics.PressureTotalReq;
import io.shulie.takin.cloud.open.resp.statistics.ReportTotalResp;
import io.shulie.takin.cloud.open.resp.statistics.PressurePieTotalResp;
import io.shulie.takin.cloud.open.api.impl.sender.CloudApiSenderService;
import io.shulie.takin.cloud.open.resp.statistics.PressureListTotalResp;
import io.shulie.takin.cloud.open.api.statistics.CloudPressureStatisticsApi;

/**
 * @author 无涯
 * @date 2020/11/30 9:53 下午
 */
@Service
public class CloudPressureStatisticsApiImpl implements CloudPressureStatisticsApi {

    @Resource
    CloudApiSenderService cloudApiSenderService;

    @Override
    public PressurePieTotalResp getPressurePieTotal(PressureTotalReq req) {
        return cloudApiSenderService.get(CloudApiConstant.STATISTIC_PRESSUREPIE_URL, req,
                new TypeReference<ResponseResult<PressurePieTotalResp>>() {})
            .getData();
    }

    @Override
    public ReportTotalResp getReportTotal(PressureTotalReq req) {
        return cloudApiSenderService.get(CloudApiConstant.STATISTIC_REPORT_URL, req,
                new TypeReference<ResponseResult<ReportTotalResp>>() {})
            .getData();
    }

    @Override
    public List<PressureListTotalResp> getPressureListTotal(PressureTotalReq req) {
        return cloudApiSenderService.get(CloudApiConstant.STATISTIC_PRESSURELIST_URL, req,
                new TypeReference<ResponseResult<List<PressureListTotalResp>>>() {})
            .getData();
    }

}
