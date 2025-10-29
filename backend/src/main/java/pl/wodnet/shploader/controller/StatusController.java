package pl.wodnet.shploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wodnet.shploader.systemstatus.StatusDto;
import pl.wodnet.shploader.systemstatus.StatusEnum;
import pl.wodnet.shploader.systemstatus.StatusService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StatusController {
    @Autowired
    StatusService statusService;


    @GetMapping(value = "/getStatus")
    public StatusDto getStatus(){
        return statusService.getStatus();
    }

}
