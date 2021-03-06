package no.visma.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@RestController
@RequestMapping("v3")
@RequiredArgsConstructor
public class WeatherControllerV3 {
    private final WeatherRepository repository;

    @GetMapping("now")
    public Weather getNow() {
        LocalDateTime nowIsh = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1);
        return repository.findByTime(nowIsh);
    }
}
