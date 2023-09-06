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
public class Total {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("InvoiceId")
    private Integer InvoiceId;

    private Double TotalPaidAmount;

    // Default constructor
    public Total() {}
}
