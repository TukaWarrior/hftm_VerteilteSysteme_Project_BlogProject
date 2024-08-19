package ch.hftm.blogproject.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import ch.hftm.blogproject.entity.Account;

public record NewAccountDTO(@NotNull @NotBlank @Length(min = 5) String name, String email) {
    public Account toAccount() {
        return new Account(name, email, "user");
    }
}
