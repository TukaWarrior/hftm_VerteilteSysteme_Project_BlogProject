package ch.hftm.blogproject.model.dto;

import java.time.ZonedDateTime;

import ch.hftm.blogproject.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id; // This will be null for POST requests
    private String name;
    private String email;
    private String role; // Optional, depending on your requirements
    private ZonedDateTime createdAt; // This will be null for POST requests

    // Constructor to convert an Account entity to AccountDTO for GET requests
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.role = account.getRole();
        this.createdAt = account.getCreatedAt();
    }

    // Method to convert AccountDTO to an Account entity for POST requests
    public Account toEntity() {
        Account account = new Account();
        account.setName(this.name);
        account.setEmail(this.email);
        account.setRole("user"); // Set a default role if not provided
        account.setCreatedAt(ZonedDateTime.now()); // Set the creation date to the current time
        return account;
    }
}