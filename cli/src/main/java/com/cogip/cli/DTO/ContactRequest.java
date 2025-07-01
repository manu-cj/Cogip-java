package com.cogip.cli.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
}
