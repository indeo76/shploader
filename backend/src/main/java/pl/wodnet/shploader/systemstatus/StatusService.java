package pl.wodnet.shploader.systemstatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.enums.ShpImportModeEnum;

@Service
public class StatusService {
    private StatusDto statusData = new StatusDto();

    public Boolean isFree(){
        return this.statusData.getStatus().equals(StatusEnum.FREE);
    }

    public Boolean isBuisy(){
        return this.statusData.getStatus().equals(StatusEnum.BUSY);
    }

    public StatusDto getStatus(){
        return statusData;
    }

    public void setBuisy(ShpImportModeEnum importMode){
        this.statusData.setStatus(StatusEnum.BUSY);
        this.statusData.getTask().setImportMode(importMode);
    }

    public void setFree(){
        this.statusData.setStatus(StatusEnum.FREE);
        this.statusData.setTask(new TaskDetailsDto());
    }

    public void setCurrentTaskDescrription(String taskDescrription){
        this.statusData.getTask().setCurrentTaskDescription(taskDescrription);
    }

}
