package com.biblioteca.controller;

import com.biblioteca.domain.service.LoanService;
import com.biblioteca.dto.request.LoanResquestDTO;
import com.biblioteca.dto.response.LoanReturnResponseDTO;
import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.dto.response.PenaltyResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(
        name = "Loan",
        description = "Loan-related operations"
)
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping()
    @Operation(
            summary = "Create a loan",
            description = "Creates a new loan at the bank."
    )
    public ResponseEntity<LoansResponseDTO> addLoan(@RequestBody LoanResquestDTO dto){
        return ResponseEntity.status(200).body(this.loanService.addLoan(dto));
    }

    @PatchMapping("/{id}/return")
    @Operation(
            summary = "Returns a loan",
            description = "makes the book available for other loans."
    )
    public ResponseEntity<LoanReturnResponseDTO> loanReturn(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.loanService.loanReturn(id));
    }
    @GetMapping("/overdue")
    @Operation(
            summary = "Returns all overdue loans",
            description = "returns all overdue loans."
    )
    public ResponseEntity<List<LoansResponseDTO>> overdueLoans(){
        return new ResponseEntity<>(this.loanService.overdueLoan(), HttpStatus.OK);
    }
}
