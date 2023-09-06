package dev.danvega.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("InvoiceId")
    private Long InvoiceId;

    private Double paidAmount;

    // Default constructor
    public Detail() {}

    // Getter and setter for InvoiceId
    public Long getInvoiceId() {
        return InvoiceId;
    }

    // Getter and setter for paidAmount
    public Double getPaidAmount() {
        return paidAmount;
    }

    // Other getters and setters for properties not shown above
}
