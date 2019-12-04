package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;


@NoArgsConstructor
@Data
@Embeddable
public class Payment {

    @Column(name = "PAYDATE", nullable = false)
    private LocalDate paydate;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
}
