package com.example.demo.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String currency;
    private int terminal_id;
    private int threat_score;
    private boolean success;
    private Long fk_account_id;

    public Transaction(double amount, String currency, int terminal_id, int threat_score, boolean success, Long fk_account_id) {
        this.amount = amount;
        this.currency = currency;
        this.terminal_id = terminal_id;
        this.threat_score = threat_score;
        this.success = success;
        this.fk_account_id = fk_account_id;
    }
}
