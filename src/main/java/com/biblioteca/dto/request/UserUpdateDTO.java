package com.biblioteca.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserUpdateDTO {
    @NotNull(message = "The user needs a name.")
    private String name;

    @NotNull(message = "The user needs an email address.")
    private String email;

    private boolean removed;
}
