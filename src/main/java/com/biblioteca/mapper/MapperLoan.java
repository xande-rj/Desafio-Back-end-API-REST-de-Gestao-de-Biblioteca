package com.biblioteca.mapper;

import com.biblioteca.domain.model.LoanModel;
import com.biblioteca.dto.response.LoansResponseDTO;
import org.modelmapper.ModelMapper;

public class MapperLoan {
    private final ModelMapper modelMapper = new ModelMapper();

    public LoansResponseDTO loansResponseDTO(LoanModel loan){
        return this.modelMapper.map(loan,LoansResponseDTO.class);
    }
}
