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
 * @TableName model_has_permissions
 */
@TableName(value ="model_has_permissions")
@Data
public class ModelHasPermissionsDo implements Serializable {
    /**
     * 
     */
    private String permissionUuid;

    /**
     * 
     */
    private String modelUuid;

    /**
     * 
     */
    private String modelType;

    /**
     * 
     */
    private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}