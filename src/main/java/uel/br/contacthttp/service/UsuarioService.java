package uel.br.contacthttp.service;

import uel.br.contacthttp.model.Usuario;
import uel.br.contacthttp.repository.UsuarioRepository;
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