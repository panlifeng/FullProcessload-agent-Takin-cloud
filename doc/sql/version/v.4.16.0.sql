/**新增jmeter日志上传amdb记录表*/
DROP TABLE IF EXISTS `t_scene_jmeterlog_upload`;
CREATE TABLE `t_scene_jmeterlog_upload`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `scene_id`      bigint(20) NOT NULL COMMENT '场景ID',
    `report_id`     bigint(20) NOT NULL COMMENT '报告ID',
    `customer_id`   bigint(20)   DEFAULT NULL COMMENT '用户ID',
    `task_status`   int(2)       DEFAULT NULL COMMENT '压测任务状态：1-启动中；2-启动成功；3-压测失败；4-压测完成',
    `upload_status` int(2)       DEFAULT NULL COMMENT '日志上传状态：0-未上传；1-上传中；2-上传完成',
    `create_time`   datetime     DEFAULT NULL COMMENT '创建时间',
    `modify_time`   datetime     DEFAULT NULL COMMENT '修改时间',
    `upload_count`  int(11)      DEFAULT '0' COMMENT '已上传文件大小',
    `file_name`     varchar(500) DEFAULT NULL COMMENT '文件名',
    PRIMARY KEY (`id`),
    KEY `idx_scene_id_report_id` (`scene_id`, `report_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 30
  DEFAULT CHARSET = utf8mb4 COMMENT ='压测上传jmeter日志任务状态表';

SET FOREIGN_KEY_CHECKS = 1;