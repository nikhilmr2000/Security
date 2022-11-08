package com.nikhil.springsecuritybasic.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.springsecuritybasic.Entitu.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}