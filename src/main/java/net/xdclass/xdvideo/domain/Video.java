package net.xdclass.xdvideo.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 概述
     */
    private String summary;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 观看数
     */
    private Integer viewNum;

    /**
     * 价格,分
     */
    private Integer price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0表示未上线，1表示上线
     */
    private Integer online;

    /**
     * 默认8.7，最高10分
     */
    private Double point;

    @TableLogic
    @JsonProperty(value = "deleted_at")
    private Date deletedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}