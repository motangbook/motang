package com.motang.generator.mapper;



import com.motang.generator.entity.Column;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 生成接口
 *  @author liuhu
 *  @Date 2020/6/9 11:56
 */
@Repository
public interface GeneratorMapper {

    /**
     * @Description 获取数据表列信息
     * @author liuhu
     * @createTime 2020-06-08 17:35:36
     * @param databaseType 数据库类型 mysql
     * @param databaseName 数据库名称
     * @param tableName 表名称
     * @return java.util.List<com.rainbow.generator.entity.Column>
     */
    List<Column> getColumns(@Param("databaseType") String databaseType, @Param("databaseName") String databaseName, @Param("tableName") String tableName);
}
