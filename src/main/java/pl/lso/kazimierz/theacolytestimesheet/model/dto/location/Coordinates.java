package pl.lso.kazimierz.theacolytestimesheet.model.dto.location;

import javax.validation.constraints.NotNull;

public class Coordinates {

    private Double accuracy;
    private Double altitude;
    private Double altitudeAccuracy;

    @NotNull(message = "Heading cannot be null")
    private Double heading;
    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;

    @NotNull(message = "Speed cannot be null")
    private Double speed;

    public Double getAccuracy() {
        return accuracy;
    }

    public Double getAltitude() {
        return altitude;
    }

    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    public Double getHeading() {
        return heading;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getSpeed() {
        return speed;
    }
}
