package Exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id){
        super("Usuario não encontrado" + id);
    }
}
