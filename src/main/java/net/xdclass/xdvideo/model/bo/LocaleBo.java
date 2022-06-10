package net.xdclass.xdvideo.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LocaleBo implements Serializable {
    private static final long serialVersionUID = -7564298385395036719L;

    @JsonProperty(value = "en-US")
    private String enUs;

    @JsonProperty(value = "zh-CN")
    private String zhCn;
}
