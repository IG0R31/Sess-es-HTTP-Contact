package uel.br.ContactHTTP.Repository;

import uel.br.ContactHTTP.Model.ContactHTTPModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactHTTPRepository extends JpaRepository<ContactHTTPModel, Long> {
}