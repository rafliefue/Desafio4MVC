package com.spring.nttdata.desafio3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.nttdata.desafio3.entities.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByName(String Name);
    
    Cliente findByLastName(String lastName);
    
    Cliente findByNameAndLastName(String Name, String LastName);

}
