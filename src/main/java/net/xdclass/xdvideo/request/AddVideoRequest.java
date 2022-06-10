package net.xdclass.xdvideo.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class AddVideoRequest {
    @Length(max = 524, message = "标题不能超过524个字")
    @NotNull(message = "标题不能为空")
    private String title;
    @Length(max = 1026, message = "简介字数不能超过1026个字")
    private String summary;
    @Length(max = 524, message = "封面链接超过524个字")
    private String coverImg;
    private Integer viewNum;
    private Integer price;

    @Range(min = 0, max = 1, message = "上线状态错误")
    private Integer online;

    @DecimalMin(value = "0.00", message = "评分格式错误")
    @DecimalMax(value = "10.00", message = "评分格式错误")
    private Double point;
}
