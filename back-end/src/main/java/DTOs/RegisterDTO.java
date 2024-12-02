package DTOs;

import Models.Role;

public record RegisterDTO(String name,String email, String senha, Role role) {
}
