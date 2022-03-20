package spb.city.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spb.city.demo.Injection.Injection;
import spb.city.demo.ProductRepository.ProductRepository;
import spb.city.demo.service.VkladInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplicationTests {

		/**
		 * Create table with id, name, age, city.
		 * @throws SQLException
		 */
		@Test
		public void createTable()  {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Injection.class);
			ProductRepository adress = context.getBean(ProductRepository.class);

			String sql = "CREATE TABLE vklad (" +
					"№ VARCHAR(50)," +
					"deposit VARCHAR(250));";

			ProductRepository repository = context.getBean(ProductRepository.class);
			Assertions.assertTrue(repository.createTable(sql));
			context.close();
		}

		/**
		 * Added information to the table.
		 * @throws SQLException
		 */
		@Test
		public void addInformation() throws SQLException {

			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Injection.class);
			ProductRepository repository = context.getBean(ProductRepository.class);
			List<VkladInfo> list = new ArrayList<>();
			list.add(new VkladInfo(5, 150000));

			for (int i = 0; i < list.size(); i++) {
				Assertions.assertTrue(repository.addVklad(list.get(i)));
			}
			context.close();
		}

	/**
	 * Read information to the table.
	 * @throws SQLException
	 */
	@Test
	public void readInformation() throws SQLException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Injection.class);
		ProductRepository repository = context.getBean(ProductRepository.class);

		System.out.println(repository.readAllVklad());
		context.close();
	}

	/**
	 * Delete table.
	 * @throws SQLException
	 */
	@Test
	public void deleteTable() throws SQLException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Injection.class);
		ProductRepository repository = context.getBean(ProductRepository.class);

		Assertions.assertTrue(repository.deleteTable());
		context.close();
	}

	/**
	 * Close the vklad.
	 * @throws SQLException
	 */
	@Test
	public void closeVklad() throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Injection.class);

		ProductRepository repository = context.getBean(ProductRepository.class);
		Assertions.assertTrue(repository.closeVklad(1));
		context.close();
	}

	/**
	 * Started and add vklad through SoapUI
	 * @param args
	 */
	public static void main(String[] args) { SpringApplication.run(App.class, args);}
}
