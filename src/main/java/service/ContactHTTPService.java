package uel.br.ContactHTTPService;

import uel.br.Contact.Model.ContactHTTPModel;
import uel.br.Contact.Repository.ContactHTTPRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ContactHTTPService {
    private final ContactHTTPRepository repository;

    public ContactHTTPService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<ContactModel> listar(){
        return repository.findAll();
    }

    public ContactModel buscar(int id){
        return findByIdOrThrow(id);
    }

    public ContactModel adicionar(ContactModel c){
        validarDados(c);
        return repository.save(c);
    }

    public void remover(int id){
        findByIdOrThrow(id);
        repository.deleteById(id);
    }

    public ContactModel atualizar(int id, ContactModel c){
        validarDados(c);
        ContactModel existente = findByIdOrThrow(id);
        existente.setNome(c.getNome());
        existente.setEmail(c.getEmail());
        existente.setTelefone(c.getTelefone());
        existente.setEndereco(c.getEndereco());
        existente.setDataNascimento(c.getDataNascimento());
        return repository.save(existente);
    }


    //404 NOT FOUND: contato não existe (busca pelo id no banco)
    private ContactModel findByIdOrThrow(int id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }

    //400 BAD REQUEST: dados inválidos (campos nulos)
    private void validarDados(ContactModel c){
        if(c.getNome()=="") throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'nome' não pode estar vazio.");
        if(c.getTelefone()=="") throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'telefone' não pode estar vazio.");
        if(c.getEmail()=="") throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'email' não pode ficar vazio.");
    }
}