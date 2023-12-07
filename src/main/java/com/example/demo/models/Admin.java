package com.example.demo.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.demo.dtos.AdminDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String imageUrl;
    private String password;
    private String fullName;
    //private BigDecimal balance;


    public Admin(AdminDTO adminDto) {
        this.username = adminDto.getUsername();
        this.password = BCrypt.withDefaults()
                .hashToString(12, adminDto.getPassword().toCharArray());
        this.fullName = adminDto.getFullName();
        // this.balance = new BigDecimal(2500000);
    }

}
