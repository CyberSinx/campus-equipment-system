package edu.cit.miel.kaysean.campusequipmentloan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Student student;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Column(precision = 10, scale = 2)
    private BigDecimal penalty = BigDecimal.valueOf(0.00);

    public BigDecimal getPenalty() {
        return penalty != null ? penalty.setScale(2, RoundingMode.HALF_UP) : BigDecimal.valueOf(0.00);
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty != null ? penalty.setScale(2, RoundingMode.HALF_UP) : BigDecimal.valueOf(0.00);
    }
}
