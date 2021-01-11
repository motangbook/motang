package com.motang.crawl.api.entity;

import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
*  @Description msg 实体
*  @author liuhu
*  @Date 2021-1-9 22:49:39
*/
@Data
@ApiModel("msg")
@TableName("book_category")
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
     @TableField("create_time")
     private Date createTime;

     @ApiModelProperty(name = "update_time",value = "修改时间")
     @TableField("update_time")
     private Date updateTime;

     @ApiModelProperty(name = "sort",value = "排序")
     @TableField("sort")
     private Byte sort;
}