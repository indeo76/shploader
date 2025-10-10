package pl.wodnet.shploader.systemstatus;

import lombok.Getter;
import lombok.Setter;
import pl.wodnet.shploader.enums.ShpImportModeEnum;

@Getter
@Setter
public class TaskDetailsDto {
    private ShpImportModeEnum importMode;
    private String currentTaskDescription;

}
