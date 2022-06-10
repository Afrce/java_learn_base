package net.xdclass.xdvideo.model.enums;

import lombok.Getter;

@Getter
public enum OperateTypeEnum {
    ALL(0, "全部"),
    ADD(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    OTHER(4, "其他");

    private Integer code;
    private String msg;

    OperateTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
