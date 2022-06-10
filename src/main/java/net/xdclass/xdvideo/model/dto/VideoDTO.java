package net.xdclass.xdvideo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VideoDTO {

    private Integer page = 1;
    private Integer size = 10;
    private String name;
    private Double point;
}
