package edu.cit.miel.kaysean.campusequipmentloan.controller;

import edu.cit.miel.kaysean.campusequipmentloan.model.Loan;
import edu.cit.miel.kaysean.campusequipmentloan.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Add optional startDate parameter
    @PostMapping
    public Loan createLoan(
            @RequestParam Long studentId,
            @RequestParam Long equipmentId,
            @RequestParam(required = false) String startDate) {

        LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
        return loanService.createLoan(studentId, equipmentId, start);
    }

    @PostMapping("/{id}/return")
    public Loan returnLoan(@PathVariable Long id, @RequestParam(required = false) String returnDate) {
        LocalDate date = returnDate != null ? LocalDate.parse(returnDate) : null;
        return loanService.returnLoan(id, date);
    }
}
