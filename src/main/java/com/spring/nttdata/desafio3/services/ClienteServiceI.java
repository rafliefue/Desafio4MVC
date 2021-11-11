package com.spring.nttdata.desafio3.services;

import com.spring.nttdata.desafio3.entities.Cliente;

public interface ClienteServiceI {
	
    Cliente findByName(String name);
    
    Cliente findByLastName(String lastName);
    
    Cliente findByNameAndLastName(String name, String lastName);
    
}
