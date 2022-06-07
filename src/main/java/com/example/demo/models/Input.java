package com.example.demo.models;

import lombok.*;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Input {
    private double amount;
    private String currency;
    private int terminal_id;
    private int threat_score;
    private Long card_number;
}
