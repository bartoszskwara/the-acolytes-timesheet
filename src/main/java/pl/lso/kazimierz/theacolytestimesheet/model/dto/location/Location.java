package pl.lso.kazimierz.theacolytestimesheet.model.dto.location;

import javax.validation.constraints.NotNull;

public class Location {
    @NotNull(message = "Coordinates cannot be null")
    private Coordinates coords;
    private Long timestamp;

    public Coordinates getCoords() {
        return coords;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
