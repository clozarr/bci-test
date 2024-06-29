package com.bci.test.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class PhoneEntity {

    @Id
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "city_code", nullable = false)
    private String cityCode;
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
