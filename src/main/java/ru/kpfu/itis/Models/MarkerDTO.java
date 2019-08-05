package ru.kpfu.itis.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkerDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("country")
    private String country;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("longitude")
    private float longitude;

    @JsonProperty("latitude")
    private float latitude;

}
