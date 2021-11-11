package com.spring.nttdata.desafio3;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.nttdata.desafio3.entities.Cliente;
import com.spring.nttdata.desafio3.services.ClienteService;

@SpringBootApplication
public class Desafio3Application implements CommandLineRunner {
	
	@Autowired
	private ClienteService clienteService;

	public static void main(String[] args) {
		SpringApplication.run(Desafio3Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
			
		
		//Creo  clientes
		Cliente cliente = new Cliente();
		cliente.setDni("11111111A");
		cliente.setName("Rafael");
		cliente.setLastName("Liebana Fuentes");
		cliente.setId(1);
		
		
		Cliente cliente2 = new Cliente();
		cliente2.setDni("12111111A");
		cliente2.setName("Ruben");
		cliente2.setLastName("Bueno Menendez");
		cliente2.setId(2);
				
		
		Cliente cliente3 = new Cliente();
		cliente3.setDni("13111111A");
		cliente3.setName("Alejandro");
		cliente3.setLastName("Gomez Herrera");
		cliente3.setId(3);
		
		Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MONTH, 04);
        ca.set(Calendar.DATE, 29);
        ca.set(Calendar.YEAR, 1994);
        Date fecha = ca.getTime();
		
		cliente.setBirthDate(fecha);
		cliente2.setBirthDate(fecha);
		cliente3.setBirthDate(fecha);
		
		//Guardando en bbdd
		this.clienteService.save(cliente);
		this.clienteService.save(cliente2);
		this.clienteService.save(cliente3);
		
		//Búsqueda por Nombre y Apellidos
		System.out.println("================================");
		System.out.println("***Búsqueda total***");
		for(Cliente a : this.clienteService.findAll()) {
			System.out.println(a.getName() + " " + a.getLastName()); //Mostramos
		}
		System.out.println("================================");
		
			
		//Búsqueda por Nombre
		System.out.println("================================");
		System.out.println("***Búsqueda por nombre***");
		Cliente clienteEnBBDD = this.clienteService.findByName("Rafael");
		System.out.println(clienteEnBBDD.getName() + " " + clienteEnBBDD.getLastName()); //Mostramos
		System.out.println("================================");
		
		//Búsqueda por Apellidos
		System.out.println("================================");
		System.out.println("***Búsqueda por apellidos***");
		Cliente clienteEnBBDD2 = this.clienteService.findByLastName("Liebana Fuentes");
		System.out.println(clienteEnBBDD2.getName() + " " + clienteEnBBDD2.getLastName()); //Mostramos
		System.out.println("================================");
		
		//Búsqueda por Nombre y Apellidos
		System.out.println("================================");
		System.out.println("***Búsqueda por nombre y apellidos***");
		Cliente clienteEnBBDD3 = this.clienteService.findByNameAndLastName("Rafael", "Liebana Fuentes");
		System.out.println(clienteEnBBDD3.getName() + " " + clienteEnBBDD3.getLastName()); //Mostramos
		System.out.println("================================");
		
		//Actualizando cliente
		
		Cliente actualizado = this.clienteService.findByName("Rafael");
		
		actualizado.setName("Rafa Actualizado");
			
		this.clienteService.update(actualizado);
		
		//Búsqueda Actualizada
		
		System.out.println("================================");
		System.out.println("***Búsqueda por nombre actualizado***");
		Cliente clienteEnBBDD4 = this.clienteService.findByName("Rafa Actualizado");
		System.out.println(clienteEnBBDD4.getName()); //Mostramos
		System.out.println("================================");
		
		//Delete cliente
		
		this.clienteService.delete(clienteEnBBDD4);
		
	}

}
