package net.xdclass.xdvideo.model.Do;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import net.xdclass.xdvideo.model.bo.LocaleBo;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * @TableName roles
 */
@TableName(value ="roles", autoResultMap = true)
@Data
public class RolesDo implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.INPUT)
    private String uuid;

    /**
     * 角色名，唯一
     */
    private String name;

    /**
     * 
     */
    private String guardName;

    /**
     * 是否系统创建的角色，0：不是，1：是
     */
    private Integer isSystem;

    /**
     * 国际化样例，{"en-US":"SuperAdmin","zh-CN":"超管"}
     */
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = FastjsonTypeHandler.class)
    private LocaleBo locale;

    /**
     * 备注信息
     */
    private String comment;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}