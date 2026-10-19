package uel.br.Contact.Repository;

import uel.br.Contact.Model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactModel, Integer> {
}