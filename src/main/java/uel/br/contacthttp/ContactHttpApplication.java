package uel.br.ContactHTTP;

import uel.br.ContactHTTP.Model.Usuario;
import uel.br.ContactHTTP.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactHTTPApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactHTTPApplication.class, args);
	}

	// Usuário padrão para testes (apenas didático): admin@uel.br / 123456
	@Bean
	CommandLineRunner seedUsuario(UsuarioRepository repository) {
		return args -> {
			if (repository.findByEmailAndSenha("admin@uel.br", "123456") == null) {
				repository.save(new Usuario("Admin", "admin@uel.br", "123456"));
			}
		};
	}
}