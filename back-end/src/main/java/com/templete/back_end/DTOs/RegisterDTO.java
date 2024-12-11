package com.templete.back_end.DTOs;

import com.templete.back_end.Models.Role;

public record RegisterDTO(String name,String email, String senha, Role role) {
}
