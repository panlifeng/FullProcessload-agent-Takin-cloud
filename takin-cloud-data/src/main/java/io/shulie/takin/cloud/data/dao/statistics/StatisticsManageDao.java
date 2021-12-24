package io.shulie.takin.cloud.data.dao.statistics;

import java.util.List;

import io.shulie.takin.cloud.data.result.statistics.PressureListTotalResult;
import io.shulie.takin.cloud.data.result.statistics.PressurePieTotalResult;
import io.shulie.takin.cloud.sdk.model.response.statistics.ReportTotalResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 无涯
 * @date 2020/10/26 4:40 下午
 */
@Mapper
public interface StatisticsManageDao {
    /**
     * 统计场景分类，返回饼状图数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return -
     */
    @Select("<script>select status,count(1) as count from t_scene_manage where create_time &gt;= #{startTime} and create_time &lt;= #{endTime} "
        + "and is_deleted =0  GROUP BY STATUS</script>")
    List<PressurePieTotalResult> getPressureScenePieTotal(@Param("startTime") String startTime,
        @Param("endTime") String endTime);

    /**
     * 统计报告通过/未通过
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return -
     */
    @Select("<script>SELECT sum(CASE conclusion WHEN 1 THEN 1 ELSE 0 END) as success,\n"
        + "sum(CASE conclusion WHEN 0 THEN 1 ELSE 0 END) as fail,count(1) as count\n"
        + "FROM t_report  "
        + "WHERE gmt_create &gt;= #{startTime} and gmt_create &lt;= #{endTime} "
        + "and status = 2 and is_deleted =0</script>")
    ReportTotalResp getReportTotal(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 压测场景次数统计  标签数据需要从web获取
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return -
     */
    @Select("<script>SELECT a.id,a.scene_name as name,a.create_time as gmtCreate,a.user_id as createName,\n"
        + "sum(CASE b.conclusion WHEN 1 THEN 1 ELSE 0 END) as success,\n"
        + "sum(CASE b.conclusion WHEN 0 THEN 1 ELSE 0 END) as fail,\n"
        + "count(1) as count\n"
        + "FROM t_scene_manage a,t_report b WHERE a.id = b.scene_id and b.status = 2 and b.is_deleted =0\n"
        + "and b.gmt_create &gt;= #{startTime} and b.gmt_create &lt;= #{endTime}\n"
        + "and a.is_deleted = 0 GROUP BY a.id,a.scene_name,a.create_time,a.create_name ORDER BY count desc,a.create_time desc LIMIT 5</script>")
    List<PressureListTotalResult> getPressureSceneListTotal(@Param("startTime") String startTime,
        @Param("endTime") String endTime);

    /**
     * 压测脚本次数统计
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param scriptIds 脚本主键
     * @return -
     */
    @Select("<script>" +
        "SELECT script_id as id,\n"
        + "sum(CASE conclusion WHEN 1 THEN 1 ELSE 0 END) as success,\n"
        + "sum(CASE conclusion WHEN 0 THEN 1 ELSE 0 END) as fail,\n"
        + "count(1) as count\n"
        + "FROM t_report  WHERE script_id is not null \n"
        + "and gmt_create &gt;= #{startTime} and gmt_create &lt;= #{endTime}\n"
        + "and status = 2 and is_deleted =0\n"
        + "and script_id in\n" +
        " <foreach collection='scriptIds' open='(' close=')' separator=',' item='scriptId'>#{scriptId}</foreach>\n"
        + "GROUP BY script_id ORDER BY count DESC,gmt_create DESC LIMIT 5"
        + " </script>")
    List<PressureListTotalResult> getPressureScriptListTotal(@Param("startTime") String startTime,
        @Param("endTime") String endTime, @Param("scriptIds") List<Long> scriptIds);

}
