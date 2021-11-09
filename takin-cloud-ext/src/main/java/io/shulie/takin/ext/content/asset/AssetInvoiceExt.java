package io.shulie.takin.ext.content.asset;

import io.shulie.takin.ext.content.AbstractEntry;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资产拓展模块
 * <p>
 * 付款单实体
 *
 * @author 张天赐
 */
@Data
public class AssetInvoiceExt<T> extends AbstractEntry {
    /**
     * 场景ID
     */
    private Long sceneId;
    /**
     * 任务ID
     */
    private Long taskId;
//    /**
//     * 并发
//     */
//    private Integer expectThroughput;
//    /**
//     * 平均并发
//     */
//    private BigDecimal avgConcurrent;
//    /**
//     * 施压类型,0:并发,1:tps,2:自定义;不填默认为0
//     */
//    private Integer pressureType;
//    /**
//     * 压测模式
//     */
//    private Integer pressureMode;
//    /**
//     * 压测总时长
//     */
//    private Long pressureTotalTime;
//    /**
//     * 递增时长
//     */
//    private Long increasingTime;
//    /**
//     * 阶梯层级
//     */
//    private Integer step;
//
    /**
     * 来源的详情ID
     * 压测报告 -> 报告ID
     * 业务活动流量验证 -> 业务活动ID
     * 脚本调试 -> 脚本ID
     * 巡检场景 ->
     */
    private Long resourceId;

    /**
     * 来源的名称
     * 压测报告 -> 场景名称
     * 业务活动流量验证 -> 业务活动名称
     * 脚本调试 -> 脚本名称
     */
    private String resourceName;

    /**
     * 数据来源,1=压测报告、2=业务活动流量验证、3=脚本调试
     */
    private Integer resourceType;

    /**
     * 操作用户ID
     */
    private Long operateId;
    /**
     * 操作人
     */
    private String operateName;
    /**
     * 租户ID
     */
    private Long customerId;
    /**
     * 业务数据
     */
    private T data;
}