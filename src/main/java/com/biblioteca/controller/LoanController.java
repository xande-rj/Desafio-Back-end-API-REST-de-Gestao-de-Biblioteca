package com.biblioteca.controller;

import com.biblioteca.domain.service.LoanService;
import com.biblioteca.dto.request.LoanResquestDTO;
import com.biblioteca.dto.response.LoansResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping()
    public ResponseEntity<LoansResponseDTO> addLoan(@RequestBody LoanResquestDTO dto){
        return ResponseEntity.status(200).body(this.loanService.addLoan(dto));
    }
}
