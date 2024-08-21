package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String role;
    private ZonedDateTime createdAt = ZonedDateTime.now();

    // Constructor used in NewAccountDTO
    public Account (String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
