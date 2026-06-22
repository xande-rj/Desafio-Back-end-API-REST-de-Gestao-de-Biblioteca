package com.biblioteca.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoansResponseDTO{
     private Long id;
     LocalDateTime create_at;
     LocalDateTime pay_day;
     LocalDateTime update_at;
     BookResponseDTO book;

}
