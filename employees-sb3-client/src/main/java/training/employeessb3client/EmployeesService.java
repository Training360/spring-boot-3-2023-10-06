package training.employeessb3client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/api/employees")
public interface EmployeesService {

    @GetExchange
    List<EmployeeResource> listEmployees();
}
