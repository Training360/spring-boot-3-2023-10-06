package training.employeessb3client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class EmployeesSb3ClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesSb3ClientApplication.class, args);
	}

	private WebClient.Builder webClientBuilder;

	@Override
	public void run(String... args) throws Exception {
		var webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
		var clientAdapter = WebClientAdapter.forClient(webClient);
		var factory = HttpServiceProxyFactory
				.builder(clientAdapter).build();
		var client = factory.createClient(EmployeesService.class);


		var employees = client.listEmployees();
		employees.forEach(employee -> log.info("Employee: {}", employee.getName()));
	}
}
