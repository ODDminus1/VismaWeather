package no.visma.v3;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static no.visma.v3.Weather.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME)
public class Weather {
    public static final String TABLE_NAME = "T_WEATHER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime time;
    private String temperatur;
    private String vindhastighet;
    private String broek;
}
