package org.arielzarate.interfaces.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private RatingResponse rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingResponse {
        private Double rate;
        private Integer count;
    }
}