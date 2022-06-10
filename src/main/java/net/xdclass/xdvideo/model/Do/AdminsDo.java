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
 * @TableName admins
 */
@TableName(value ="admins")
@Data
public class AdminsDo implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.INPUT)
    private String uuid;

    /**
     * 组uuid,admin_groups.uuid
     */
    private String groupUuid;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    /**
     * 废弃，保留以兼容原数据，对应 erp的admin.admin_id，对应uums的users.origin_id
     */
    private Object id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}