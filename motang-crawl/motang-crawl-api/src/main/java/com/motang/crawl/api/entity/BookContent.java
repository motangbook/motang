package com.motang.crawl.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  @Description 小说正文实体
*  @author liuhu
*  @Date 2021-1-12 14:55:20
*/
@Data
@ApiModel("小说正文")
@TableName("book_content")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookContent implements Serializable{

     @ApiModelProperty(name = "id",value = "正文Id")
     @TableId(value = "id", type = IdType.AUTO)
     private Long id;

     @ApiModelProperty(name = "chapter_id",value = "章节Id")
     @TableField("chapter_id")
     private Long chapterId;

     @ApiModelProperty(name = "content",value = "章节内容")
     @TableField("content")
     private String content;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField(value = "create_time",fill = FieldFill.INSERT)
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_time",value = "修改时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime updateTime;
}