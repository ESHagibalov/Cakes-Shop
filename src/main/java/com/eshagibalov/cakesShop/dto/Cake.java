package com.eshagibalov.cakesShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Schema(description = "Cake's short data")
@Validated
public class Cake {
    @NotNull
    @Schema(description = "id of the cake", required = true)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "Name of the cake", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "Calories in cake", required = true)
    @JsonProperty("calories")
    private BigDecimal calories;

    @NotNull
    @Schema(description = "Url of cake's image", required = true)
    @JsonProperty("image")
    private String image;

    @NotNull
    @Schema(description = "Price of the cake", required = true)
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Weight of the cake", required = true)
    @JsonProperty("weight")
    private BigDecimal weight;

}
