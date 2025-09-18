package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {
    // Add startDate parameter
    Loan createLoan(Long studentId, Long equipmentId, LocalDate startDate);

    Loan returnLoan(Long loanId, LocalDate returnDate);

    List<Loan> getStudentLoans(Long studentId);
}
