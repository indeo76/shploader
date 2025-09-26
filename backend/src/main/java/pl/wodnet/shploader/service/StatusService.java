package pl.wodnet.shploader.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.enums.StatusEnum;

@Getter
@Setter
@Service
public class StatusService {
    private StatusEnum status = StatusEnum.FREE;
}
