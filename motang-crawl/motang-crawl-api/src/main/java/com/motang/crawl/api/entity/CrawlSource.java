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
*  @Date 2021-1-11 14:28:14
*/
@Data
@ApiModel("msg")
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

     @ApiModelProperty(name = "create_time",value = "创建时间")
     @TableField("create_time")
     private Date createTime;

     @ApiModelProperty(name = "update_time",value = "更新时间")
     @TableField("update_time")
     private Date updateTime;

}