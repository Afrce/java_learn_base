package net.xdclass.xdvideo.model.Do;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName model_has_roles
 */
@TableName(value ="model_has_roles")
@Data
public class ModelHasRolesDo implements Serializable {
    /**
     * 
     */
    private String roleUuid;

    /**
     * 
     */
    private String modelUuid;

    /**
     * 
     */
    private String modelType;

    /**
     * 是否是用户默认的角色，0：否，1：是
     */
    private Integer isDefault;

    /**
     * 
     */
    private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}