package net.xdclass.xdvideo.model.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBo implements Serializable {

    private static final long serialVersionUID = 5032912666587417711L;

    private String username;

    private String email;

    private Long id;
}
