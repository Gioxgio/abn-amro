package com.bank.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Builder
@Entity(name = "account")
@EntityListeners({ AuditingEntityListener.class })
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Account {

    public Account() {}

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String number;

    private String iban;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;

    private String currency;

    private String type;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    private Integer id_customer;
}
