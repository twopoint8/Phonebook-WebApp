package in.sanvic.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sanvic.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Serializable> {

}
