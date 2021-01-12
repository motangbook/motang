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
*  @Description 作者 实体
*  @author liuhu
*  @Date 2021-1-12 14:28:00
*/
@Data
@ApiModel("作者实体类")
@TableName("author")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable{

     @ApiModelProperty(name = "id",value = "作者ID")
     @TableId(value = "id", type = IdType.AUTO)
     private Long id;

     @ApiModelProperty(name = "pen_name",value = "笔名")
     @TableField("pen_name")
     private String penName;

     @ApiModelProperty(name = "user_id",value = "用户ID")
     @TableField("user_id")
     private Long userId;

     @ApiModelProperty(name = "email",value = "邮箱")
     @TableField("email")
     private String email;

     @ApiModelProperty(name = "sign",value = "签约 1-已签约 2未签约")
     @TableField("sign")
     private Byte sign;

     @ApiModelProperty(name = "description",value = "个人资料说明")
     @TableField("description")
     private String description;

     @ApiModelProperty(name = "phone",value = "手机号")
     @TableField("phone")
     private String phone;

     @ApiModelProperty(name = "chat_account",value = "聊天账号")
     @TableField("chat_account")
     private String chatAccount;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField(value = "create_time",fill = FieldFill.INSERT)
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_time",value = "修改时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private LocalDateTime updateTime;

     @ApiModelProperty(name = "status",value = "状态 1-未删除 2已删除")
     @TableField("status")
     private Byte status;

}