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
 * @TableName chapter
 */
@TableName(value ="chapter")
@Data
public class Chapter implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 视频主键
     */
    private Integer videoId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 章节顺序
     */
    private Integer ordered;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}