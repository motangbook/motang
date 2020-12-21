package com.motang.auth.entity;

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
*  @Description 菜单 实体
*  @author liuhu
*  @Date 2020-12-21 20:21:42
*/
@Data
@ApiModel("菜单")
@TableName("system_menu")
public class SystemMenu implements Serializable{

     @ApiModelProperty(name = "MENU_ID",value = "菜单/按钮ID")
     @TableId(value = "MENU_ID", type = IdType.AUTO)
     private Long menuId;
     @ApiModelProperty(name = "PARENT_ID",value = "上级菜单ID")
     @TableField("PARENT_ID")
     private Long parentId;
     @ApiModelProperty(name = "MENU_NAME",value = "菜单/按钮名称")
     @TableField("MENU_NAME")
     private String menuName;
     @ApiModelProperty(name = "PATH",value = "对应路由path")
     @TableField("PATH")
     private String path;
     @ApiModelProperty(name = "COMPONENT",value = "对应路由组件component")
     @TableField("COMPONENT")
     private String component;
     @ApiModelProperty(name = "PERMS",value = "权限标识")
     @TableField("PERMS")
     private String perms;
     @ApiModelProperty(name = "ICON",value = "图标")
     @TableField("ICON")
     private String icon;
     @ApiModelProperty(name = "TYPE",value = "类型 0菜单 1按钮")
     @TableField("TYPE")
     private String type;
     @ApiModelProperty(name = "ORDER_NUM",value = "排序")
     @TableField("ORDER_NUM")
     private Double orderNum;
     @ApiModelProperty(name = "CREATE_TIME",value = "创建时间")
     @TableField("CREATE_TIME")
     private Date createTime;
     @ApiModelProperty(name = "UPDATE_TIME",value = "修改时间")
     @TableField("UPDATE_TIME")
     private Date updateTime;
}