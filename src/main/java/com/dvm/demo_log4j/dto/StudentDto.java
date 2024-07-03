package com.dvm.demo_log4j.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
    private String name;
    private String address;
    private String email;
    private String phone;
}
