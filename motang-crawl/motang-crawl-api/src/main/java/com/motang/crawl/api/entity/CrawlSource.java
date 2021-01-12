package com.motang.crawl.api.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
*  @Description msg 实体
*  @author liuhu
*  @Date 2021-1-11 14:28:14
*/
@Data
@ApiModel("小说爬虫源站")
@TableName("crawl_source")
public class CrawlSource implements Serializable{

     @ApiModelProperty(name = "id",value = "主键")
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;

     @ApiModelProperty(name = "source_name",value = "源站名")
     @TableField("source_name")
     private String sourceName;

     @ApiModelProperty(name = "crawl_rule_id",value = "爬取规则Id")
     @TableField("crawl_rule_id")
     private String crawlRuleId;

     @ApiModelProperty(name = "source_status",value = "爬虫源状态，0：关闭，1：开启")
     @TableField("source_status")
     private Byte sourceStatus;

     @ApiModelProperty(name = "update_time",value = "创建时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT)
     private Date createTime;

     @ApiModelProperty(name = "update_time",value = "更新时间")
     @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
     private Date updateTime;

}