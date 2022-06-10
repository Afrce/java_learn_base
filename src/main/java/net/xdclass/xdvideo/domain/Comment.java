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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 内容
     */
    private String content;

    /**
     * 
     */
    private Integer userId;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 昵称
     */
    private String name;

    /**
     * 评分，10分满分
     */
    private Double point;

    /**
     * 点赞数
     */
    private Integer up;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 视频id
     */
    private Integer videoId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}