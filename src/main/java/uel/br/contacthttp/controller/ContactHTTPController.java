package uel.br.contacthttp.controller;

import uel.br.contacthttp.model.ContactHTTPModel;
import uel.br.contacthttp.service.ContactHTTPService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
public class ContactHTTPController {
    private final ContactHTTPService service;

    public ContactHTTPController(ContactHTTPService service) {
        this.service = service;
    }

    //GET /contatos: lista os contatos, indicando os favoritos.
    @GetMapping("/contatos")
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("contatos", service.listar());
        model.addAttribute("usuario", session.getAttribute("usuarioLogado"));
        model.addAttribute("favoritos", getFavoritos(session));
        return "contatos";
    }

    //POST /contatos/{id}/favoritar: adiciona o id aos favoritos ou o remove, caso esteja favoritado.
    @PostMapping("/contatos/{id}/favoritar")
    public String favoritar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }
        List<Long> favoritos = getFavoritos(session);
        if (favoritos.contains(id)) {
            favoritos.remove(id);
        } else {
            favoritos.add(id);
        }
        return "redirect:/contatos";
    }

    //GET /favoritos: lista apenas os contatos favoritos.
    @GetMapping("/favoritos")
    public String favoritos(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("contatos", service.listarFavoritos(getFavoritos(session)));
        model.addAttribute("usuario", session.getAttribute("usuarioLogado"));
        model.addAttribute("favoritos", getFavoritos(session));
        return "favoritos";
    }

    //Recupera a lista de favoritos da sessão, criando-a quando ainda não existir.
    @SuppressWarnings("unchecked")
    private List<Long> getFavoritos(HttpSession session) {
        List<Long> favoritos = (List<Long>) session.getAttribute("favoritos");
        if (favoritos == null) {
            favoritos = new ArrayList<>();
            session.setAttribute("favoritos", favoritos);
        }
        return favoritos;
    }
}
//GET /contatos: lista os contatos, indicando os favoritos.
//• POST /contatos/{id}/favoritar: adiciona o id aos favoritos ou o remove, caso esteja favoritado.
//• GET /favoritos: lista apenas os contatos favoritos.