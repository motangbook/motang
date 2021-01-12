package com.motang.crawl.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*  @Description 小说详情
*  @author liuhu
*  @Date 2021-1-12 14:48:24
*/
@Data
@ApiModel("小说详情")
@TableName("book_chapter")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookChapter implements Serializable{

     @ApiModelProperty(name = "id",value = "章节id")
     @TableId(value = "id", type = IdType.AUTO)
     private Long id;

     @ApiModelProperty(name = "name",value = "章节名称")
     @TableField("name")
     private String name;

     @ApiModelProperty(name = "book_id",value = "小说Id")
     @TableField("book_id")
     private Long bookId;

     @ApiModelProperty(name = "is_vip",value = "是否是vip 1-是 2否")
     @TableField("is_vip")
     private Byte isVip;

     @ApiModelProperty(name = "cost_point",value = "所需积分")
     @TableField("cost_point")
     private Long costPoint;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField(value = "create_time",fill = FieldFill.INSERT)
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_time",value = "修改时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime updateTime;

}