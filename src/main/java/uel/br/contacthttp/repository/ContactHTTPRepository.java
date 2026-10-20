package uel.br.contacthttp.repository;

import uel.br.contacthttp.model.ContactHTTPModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactHTTPRepository extends JpaRepository<ContactHTTPModel, Long> {
}