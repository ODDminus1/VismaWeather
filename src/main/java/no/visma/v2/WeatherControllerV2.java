package no.visma.v2;

import lombok.RequiredArgsConstructor;
import no.visma.domain.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Comparator;

import static java.util.Comparator.comparing;

@RestController
@RequestMapping("v2")
@RequiredArgsConstructor
public class WeatherControllerV2 {
    private final WeatherFetcher weatherFetcher;

    @GetMapping("now")
    public String get() {
        WeatherResponse weatherResponse = weatherFetcher.getCurrent();
        WeatherResponse.Properties.TimeSerie timeSerie = weatherResponse.getProperties().getTimeseries()
                .stream().filter(t -> t.getTime().isAfter(LocalDateTime.now()))
                .min(comparing(t -> t.getTime()))
                .orElseThrow(RuntimeException::new);
        return formatResponse(timeSerie);
    }

    private String formatResponse(WeatherResponse.Properties.TimeSerie timeSerie) {
        WeatherResponse.Properties.TimeSerie.WeatherData.Instant.Details details = timeSerie.getData().getInstant().getDetails();
        return String.format("temperatur: %s C, vindfart: %s m/s, skyarealbrøk: %s",
                details.getAirTemperature(),
                details.getWindSpeed(),
                details.getCloudAreaFraction());
    }
}
