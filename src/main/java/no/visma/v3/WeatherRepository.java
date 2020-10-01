package no.visma.v3;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface WeatherRepository extends CrudRepository<Weather,Long> {
    Weather findByTime(LocalDateTime time);
}
