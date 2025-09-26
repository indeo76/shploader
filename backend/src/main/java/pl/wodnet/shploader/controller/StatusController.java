package pl.wodnet.shploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wodnet.shploader.enums.StatusEnum;
import pl.wodnet.shploader.service.StatusService;

@RestController
public class StatusController {
    @Autowired
    StatusService statusService;


    @GetMapping(value = "/getStatus")
    public StatusEnum getStatus(){
        return statusService.getStatus();
    }

}
