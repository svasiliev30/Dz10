package spb.city.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spb.city.demo.Injection.Injection;
import spb.city.demo.ProductRepository.ProductRepository;

@SpringBootApplication
public class App {

	/**
	 * Запуск приложения
	 * */
		public static void main( String[] args ) {SpringApplication.run(App.class, args);}
}
