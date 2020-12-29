package com.motang.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 作者表
 * @author liuhu
 * @Date 2020/12/15 21:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="Author对象", description="作者表")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "penName",value = "笔名")
    private String penName;

    @ApiModelProperty(name = "userId",value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "email",value = "邮箱")
    private String email;

    @ApiModelProperty(name = "sign",value = "签约 1-已签约 2未签约")
    private Integer sign;

    @ApiModelProperty(name = "description",value = "个人资料说明")
    private String description;

    @ApiModelProperty(name = "phone",value = "手机号")
    private String phone;

    @ApiModelProperty(name = "chatAccount",value = "聊天账号")
    private String chatAccount;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "状态 1-未删除 2已删除")
    private Boolean status;


}
