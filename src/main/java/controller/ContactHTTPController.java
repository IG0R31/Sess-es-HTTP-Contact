package uel.br.Contact.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "contatos")

public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String senha;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void getSenha(String senha){ this.senha = senha; }

    public String setSenha(){ return senha; }
}
//String nome
//• String telefone
//• String email
//• String endereco
//• LocalDate dataNascimento (java.time, formato aaaa-MM-dd)