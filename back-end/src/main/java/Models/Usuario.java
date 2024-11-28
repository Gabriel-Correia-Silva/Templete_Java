package Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "Usuario")
@EqualsAndHashCode(of = "idUsuario")
public class Usuario implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long idUsuario;

    private String Nome;

    private String Email;

    private String Senha;

    private Role role;

    public Usuario (String Nome, String Email, String Senha, Role role) {
        this.Nome = Nome;
        this.Email = Email;
        this.Senha = Senha;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("Role_ADMIN"),new SimpleGrantedAuthority("Role_USUARIO"));
        else return List.of(new SimpleGrantedAuthority("Role_USUARIO"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
