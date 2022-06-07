package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Output {
    private boolean rejection_status;
    private String rejection_message;
    private int fraud_score;
}
