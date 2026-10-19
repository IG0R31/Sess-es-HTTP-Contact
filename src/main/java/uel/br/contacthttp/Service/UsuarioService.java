package uel.br.ContactHTTP.Service;

import uel.br.ContactHTTP.Model.Usuario;
import uel.br.ContactHTTP.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Retorna o Usuario autenticado ou null se email/senha não corresponderem.
    public Usuario autenticar(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha);
    }
}