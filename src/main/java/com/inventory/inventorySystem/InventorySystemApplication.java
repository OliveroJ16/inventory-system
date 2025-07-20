package com.inventory.inventorySystem;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventorySystemApplication {

	public static void main(String[] args) {
		try {
			//cargar variables de entorno
			Dotenv.configure().systemProperties().load();
			System.out.println("Archivo cargado y variables expuestas");
		} catch (Exception e) {
			System.err.println("Error al cargar el archivo .env: " + e.getMessage());
		}
		SpringApplication.run(InventorySystemApplication.class, args);
	}

}
