package com.example.stockalarms.model;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String stockId;

    private double currentPrice;

    private int percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
