package uel.br.ContactHTTP.Controller;

import uel.br.ContactHTTP.Model.Usuario;
import uel.br.ContactHTTP.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    //GET /login: exibe o formulário de login.
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //POST /login: autentica o usuário por e-mail e senha; em caso de sucesso, armazena-o
    //na sessão e redireciona para /contatos; em caso de falha, volta ao login com mensagem de erro.
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Usuario usuario = service.autenticar(email, senha);
        if (usuario == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos!");
            return "login";
        }
        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/contatos";
    }

    //GET /logout: invalida a sessão e redireciona para /login.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}