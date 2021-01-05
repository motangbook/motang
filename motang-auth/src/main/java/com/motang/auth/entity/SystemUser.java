package com.motang.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  @Description 用户 实体
*  @author liuhu
*  @Date 2020-12-21 20:09:07
*/
@Data
@ApiModel("用户")
@TableName("system_user")
public class SystemUser implements Serializable{

    /**
     * 用户状态：有效
     */
    public static final Integer STATUS_VALID = 1;


     @ApiModelProperty(name = "userId",value = "用户ID")
     @TableId(value = "USER_ID", type = IdType.AUTO)
     private Long userId;

     @ApiModelProperty(name = "username",value = "用户名")
     @TableField("username")
     private String username;

     @ApiModelProperty(name = "password",value = "密码")
     @TableField("password")
     private String password;

     @ApiModelProperty(name = "real_name",value = "真实姓名")
     @TableField("real_name")
     private String realName;

     @ApiModelProperty(name = "nick_name",value = "昵称")
     @TableField("nick_name")
     private String nickName;

     @ApiModelProperty(name = "avatar",value = "头像")
     @TableField("avatar")
     private String avatar;

     @ApiModelProperty(name = "email",value = "邮箱")
     @TableField("email")
     private String email;

     @ApiModelProperty(name = "mobile",value = "手机号")
     @TableField("mobile")
     private String mobile;

     @ApiModelProperty(name = "birth",value = "出身日期")
     @TableField("birth")
     private LocalDateTime birth;

     @ApiModelProperty(name = "live_address",value = "现居住地")
     @TableField("live_address")
     private String liveAddress;

     @ApiModelProperty(name = "hobby",value = "爱好")
     @TableField("hobby")
     private String hobby;

     @ApiModelProperty(name = "province",value = "省份")
     @TableField("province")
     private String province;

     @ApiModelProperty(name = "city",value = "所在城市")
     @TableField("city")
     private String city;

     @ApiModelProperty(name = "district",value = "所在地区")
     @TableField("district")
     private String district;

     @ApiModelProperty(name = "status",value = "状态1:正常 2禁用")
     @TableField("status")
     private Integer status;

     @ApiModelProperty(name = "locked",value = "是否锁定 1-正常 2锁定")
     @TableField("locked")
     private Integer locked;

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField("create_time")
     private LocalDateTime createTime;

     @ApiModelProperty(name = "update_timee",value = "修改时间")
     @TableField("update_timee")
     private LocalDateTime updateTime;

     @ApiModelProperty(name = "last_login_time",value = "最近访问时间")
     @TableField("last_login_time")
     private LocalDateTime lastLoginTime;

     @ApiModelProperty(name = "ssex",value = "性别 0男 1女 2保密")
     @TableField("ssex")
     private String ssex;

     @ApiModelProperty(name = "is_tab",value = "是否开启tab，0关闭 1开启")
     @TableField("is_tab")
     private String isTab;

     @ApiModelProperty(name = "theme",value = "主题")
     @TableField("theme")
     private String theme;

     @ApiModelProperty(name = "description",value = "描述")
     @TableField("description")
     private String description;
}