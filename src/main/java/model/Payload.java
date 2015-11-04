package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 * Created by valluri.chowdary on 04/11/15.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Payload {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("iata_airport_code")
    private String iataAirPortCode;
    @JsonProperty("type")
    private String type;
    @JsonProperty("country")
    private String country;
    @JsonProperty("geo_position")
    private GeoPosition geoPosition;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("inEurope")
    private String inEurope;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("coreCountry")
    private String coreCountry;
    @JsonProperty("distance")
    private String distance;
}
