package com.biblioteca.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoansResponseDTO {
    private Long id;
    private LocalDateTime create_at;
    private LocalDateTime pay_day;
    private LocalDateTime update_at;
    private BookResponseDTO book;
}
