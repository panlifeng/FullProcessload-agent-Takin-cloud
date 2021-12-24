package io.shulie.takin.cloud.data.dao.middleware.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.shulie.takin.cloud.data.model.mysql.MiddlewareJarEntity;
import io.shulie.takin.cloud.data.dao.middleware.MiddlewareJarDAO;
import io.shulie.takin.cloud.data.mapper.mysql.MiddlewareJarMapper;
import io.shulie.takin.cloud.data.util.MPUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 中间件包表(MiddlewareJar)表数据库 dao
 *
 * @author liuchuan
 * @since 2021-06-01 10:41:19
 */
@Service
public class MiddlewareJarDAOImpl implements MiddlewareJarDAO, MPUtil<MiddlewareJarEntity> {

    @Resource
    private MiddlewareJarMapper middlewareJarMapper;

    @Override
    public boolean saveBatch(List<MiddlewareJarEntity> createParams) {
        if (CollectionUtil.isEmpty(createParams)) {return false;}
        return SqlHelper.retBool(middlewareJarMapper.insertBatch(createParams));
    }

    @Override
    public boolean removeByAgvList(List<String> agvList) {
        if (CollectionUtil.isEmpty(agvList)) {
            return false;
        }

        return SqlHelper.retBool(middlewareJarMapper.deleteByAgvList(agvList));
    }

    @Override
    public List<MiddlewareJarEntity> listByArtifactIds(List<String> artifactIds) {
        if (CollectionUtil.isEmpty(artifactIds)) {
            return Collections.emptyList();
        }

        List<MiddlewareJarEntity> entities = middlewareJarMapper.selectList(this.getLQW()
            .select(MiddlewareJarEntity::getId, MiddlewareJarEntity::getArtifactId, MiddlewareJarEntity::getVersion,
                MiddlewareJarEntity::getGroupId, MiddlewareJarEntity::getStatus)
            .in(MiddlewareJarEntity::getArtifactId, artifactIds));
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities;
    }

}

