package net.xdclass.xdvideo.model.Do;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import net.xdclass.xdvideo.model.bo.LocaleBo;
import org.apache.ibatis.type.JdbcType;
import org.apache.shiro.authz.Permission;

/**
 * 
 * @TableName permissions
 */
@TableName(value ="permissions", autoResultMap = true)
@Data
public class PermissionsDo implements Serializable, Permission {
    /**
     * 
     */
    @TableId(type=IdType.INPUT)
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String uuid;

    /**
     * 权限名，唯一
     */
    private String name;

    /**
     * 
     */
    private String guardName;

    /**
     * 权限的类型，1，功能权限（包括访问权限，操作权限），2，入口权限（有权限即有入口），3首页入口权限（有权限即有首页）
     */
    private Integer type;

    /**
     * 对权限分组
     */
    @TableField(value = "`group`")
    private String group;

    /**
     * 本地化翻译样例，{"en-US":"Adding user privileges","zh-CN":"添加用户权限"}
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

    public boolean implies(Permission var1) {
        return true;
    }
}