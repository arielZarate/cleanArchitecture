package org.arielzarate.infrastructure.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private RatingDto rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingDto {
        private Double rate;
        private Integer count;
    }
}