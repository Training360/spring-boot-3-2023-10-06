package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Testcontainers
class EmployeesApplicationIT {

	@Autowired
	EmployeesController employeesController;

	@Container
	@ServiceConnection
	static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb");


	@Test
	void contextLoads() {
	}

	@Test
	void saveThenFindById() {
		var created = employeesController.createEmployee(new EmployeeResource("John Doe"));
		var loaded = employeesController.findEmployeeById(created.getId());

		assertEquals("John Doe", loaded.getName());
	}

}
