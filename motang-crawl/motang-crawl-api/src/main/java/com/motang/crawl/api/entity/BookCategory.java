package com.motang.crawl.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  @Description msg 实体
*  @author liuhu
*  @Date 2021-1-9 22:49:39
*/
@Data
@ApiModel("小说分类实体类")
@TableName("book_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory implements Serializable{

     @ApiModelProperty(name = "id",value = "分类id")
     @TableId(value = "id", type = IdType.AUTO)
     private Long id;

     @ApiModelProperty(name = "name",value = "分类名称")
     @TableField("name")
     private String name;

     @ApiModelProperty(name = "work_direction",value = "作品方向，1：男频，2：女频'")
     @TableField("work_direction")
     private Byte workDirection;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField(value = "create_time",fill = FieldFill.INSERT)
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_time",value = "修改时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime updateTime;

     @ApiModelProperty(name = "sort",value = "排序")
     @TableField("sort")
     private Byte sort;
}