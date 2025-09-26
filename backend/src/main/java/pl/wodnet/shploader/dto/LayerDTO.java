package pl.wodnet.shploader.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LayerDTO implements Serializable {
    private String name;
    private Integer count;

    public LayerDTO(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
}
