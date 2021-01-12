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
*  @Description 小说详情 实体
*  @author liuhu
*  @Date 2021-1-12 15:19:11
*/
@Data
@ApiModel("小说详情")
@TableName("book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable{

     @ApiModelProperty(name = "id",value = "小说Id")
     @TableId(value = "id", type = IdType.AUTO)
     private Long id;

     @ApiModelProperty(name = "name",value = "小说名称")
     @TableField("name")
     private String name;

     @ApiModelProperty(name = "category_id",value = "小说分类ID")
     @TableField("category_id")
     private Long categoryId;

     @ApiModelProperty(name = "author_id",value = "作者Id")
     @TableField("author_id")
     private Long authorId;

     @ApiModelProperty(name = "book_introduction",value = "小说简介")
     @TableField("book_introduction")
     private String bookIntroduction;

     @ApiModelProperty(name = "book_final",value = "小说状态: 1连载中 2已完结")
     @TableField("book_final")
     private Byte bookFinal;

     @ApiModelProperty(name = "book_final",value = "推荐等级")
     @TableField("book_final")
     private String level;

     @ApiModelProperty(name = "image_attachment_id",value = "附件表ID")
     @TableField("image_attachment_id")
     private Long imageAttachmentId;

     @ApiModelProperty(name = "work_direction",value = "作品方向：1男频 2女频")
     @TableField("work_direction")
     private Byte workDirection;

     @ApiModelProperty(name = "is_vip",value = "是否是vip 1是  2否")
     @TableField("is_vip")
     private Byte isVip;

     @ApiModelProperty(name = "take_down",value = "是否下架   1 已上架 2下架")
     @TableField("take_down")
     private Byte takeDown;

     @ApiModelProperty(name = "subscribe_count",value = "订阅数")
     @TableField("subscribe_count")
     private Long subscribeCount;

     @ApiModelProperty(name = "total_text_count",value = "总字数")
     @TableField("total_text_count")
     private String totalTextCount;

     @ApiModelProperty(name = "comment_count",value = "评论数")
     @TableField("comment_count")
     private Long commentCount;

     @ApiModelProperty(name = "click_count",value = "点击量")
     @TableField("click_count")
     private Integer clickCount;


     @ApiModelProperty(name = "last_index_id",value = "最新目录ID")
     @TableField("last_index_id")
     private Long lastIndexId;

     @ApiModelProperty(name = "last_index_name",value = "最新目录名")
     @TableField("last_index_name")
     private String lastIndexName;

     @ApiModelProperty(name = "last_update_time",value = "最后更新时间")
     @TableField("last_update_time")
     private LocalDateTime lastUpdateTime;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField(value = "create_time",fill = FieldFill.INSERT)
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_time",value = "更新时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime updateTime;

     @ApiModelProperty(name = "status",value = "状态: 1正常 2已删除")
     @TableField("status")
     private Byte status;

}