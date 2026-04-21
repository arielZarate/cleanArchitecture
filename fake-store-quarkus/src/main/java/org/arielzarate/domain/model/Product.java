package org.arielzarate.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating {
        private Double rate;
        private Integer count;
    }
}