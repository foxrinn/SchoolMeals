package com.artemsolovev.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    @NonNull
    private String pictureURL;
    @Column(nullable = false)
    @NonNull
    private double price;
    @Column(nullable = false)
    @NonNull
    private double weight;
    @Column(nullable = false)
    @NonNull
    private String category;
    @Column(nullable = false)
    @NonNull
    private String description;
    @Column(nullable = false)
    @NonNull
    private String ingredients;
    @Column(nullable = false)
    @NonNull
    private String allergens;
    @Column(nullable = false)
    @NonNull
    private double calories;
    @ManyToMany
    @JoinTable(
            name = "dishes_orders",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;
    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
}
