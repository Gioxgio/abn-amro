package com.abnamro.data.entity;

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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Entity(name = "customer")
@EntityListeners({ AuditingEntityListener.class })
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Customer {

    public Customer() {}

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    private String address;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(name = "id_document")
    private String idDocument;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String country;
}
