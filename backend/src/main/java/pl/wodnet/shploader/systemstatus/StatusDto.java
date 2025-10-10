package pl.wodnet.shploader.systemstatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDto {
    private StatusEnum status = StatusEnum.FREE;
    private TaskDetailsDto task = new TaskDetailsDto();
}
