package net.xdclass.xdvideo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName episode
 */
@TableName(value ="episode")
@Data
public class Episode implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 集标题
     */
    private String title;

    /**
     * 第几集
     */
    private Integer num;

    /**
     * 时长 分钟，单位
     */
    private String duration;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 集概述
     */
    private String summary;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 章节主键id
     */
    private Integer chapterId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}