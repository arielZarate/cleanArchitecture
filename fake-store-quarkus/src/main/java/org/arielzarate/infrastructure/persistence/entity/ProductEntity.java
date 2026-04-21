package org.arielzarate.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private String image;

    @Embedded
    private RatingEmbed rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class RatingEmbed {
        private Double rate;
        private Integer count;
    }
}