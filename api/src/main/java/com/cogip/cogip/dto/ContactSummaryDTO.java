package com.cogip.cogip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactSummaryDTO {
    private String firstName;
    private String lastName;
    private String email;
}