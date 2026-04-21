package org.arielzarate.interfaces.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private RatingRequest rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingRequest {
        private Double rate;
        private Integer count;
    }
}