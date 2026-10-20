package uel.br.contacthttp.service;

import uel.br.contacthttp.model.ContactHTTPModel;
import uel.br.contacthttp.repository.ContactHTTPRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContactHTTPService {
    private final ContactHTTPRepository repository;

    public ContactHTTPService(ContactHTTPRepository repository) {
        this.repository = repository;
    }

    public List<ContactHTTPModel> listar(){
        return repository.findAll();
    }

    public ContactHTTPModel buscar(Long id){
        return findByIdOrThrow(id);
    }

    // Lista apenas os contatos cujos ids estão na lista de favoritos da sessão.
    public List<ContactHTTPModel> listarFavoritos(List<Long> ids){
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repository.findAllById(ids);
    }

    //404 NOT FOUND: contato não existe (busca pelo id no banco)
    private ContactHTTPModel findByIdOrThrow(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }
}