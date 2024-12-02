package Controllers;

import java.util.List;

import Exceptions.UsuarioNotFoundException;
import Models.Usuario;
import Repository.UsuarioRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/Usuarios")
    List<Usuario> all() {
        return repository.findAll();
    }

    @GetMapping("/Usuarios/{id}")
    public Usuario one(@PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @PostMapping("/Usuarios/Cadastro")
    public Usuario newUsuario(@RequestBody Usuario newUsuario) {
        return repository.save(newUsuario);
    }

    @PutMapping("/Usuarios/{id}")
    public Usuario replaceUsusario(@RequestBody Usuario newUsuario, @PathVariable long id){
        return repository.findById(id)
                .map(Usuario -> {
                    Usuario.setNome(newUsuario.getNome());
                    Usuario.setEmail(newUsuario.getEmail());
                    Usuario.setSenha(newUsuario.getSenha());
                    Usuario.setRole(newUsuario.getRole());
                    return repository.save(Usuario);
                }).orElseGet(()->{
                    return repository.save(newUsuario);
                });
    }

    @DeleteMapping("/Usuarios/{id}")
    void deletarUsuario(@PathVariable Long id){
        repository.deleteById(id);
    }
}


