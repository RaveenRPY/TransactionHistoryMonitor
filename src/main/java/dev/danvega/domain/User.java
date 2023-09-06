package dev.danvega.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sender;             // User who sent the payment
    private String receiver;           // User who received the payment
    private Long totalAmount;          // Total amount involved in transactions
    private Double totalPaidAmount;    // Total amount paid by the user

    // Getter and setter for totalPaidAmount

    public void setTotalPaidAmount(Double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    // Default constructor
    public User() {}
}
