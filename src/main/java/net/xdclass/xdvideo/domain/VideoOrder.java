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
 * @TableName video_order
 */
@TableName(value ="video_order")
@Data
public class VideoOrder implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 用户标示
     */
    private String openid;

    /**
     * 订单唯一标识
     */
    private String outTradeNo;

    /**
     * 0表示未支付，1表示已支付
     */
    private Integer state;

    /**
     * 订单生成时间
     */
    private Date createTime;

    /**
     * 支付回调时间
     */
    private Date notifyTime;

    /**
     * 支付金额，单位分
     */
    private Integer totalFee;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 微信头像
     */
    private String headImg;

    /**
     * 视频主键
     */
    private Integer videoId;

    /**
     * 视频名称
     */
    private String videoTitle;

    /**
     * 视频图片
     */
    private String videoImg;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户ip地址
     */
    private String ip;

    /**
     * 0表示未删除，1表示已经删除
     */
    private Integer del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}