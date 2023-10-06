package employees;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeesService {

    private EmployeesRepository repository;

//    private ObservationRegistry observationRegistry;

    @Observed(name = "list.employees", contextualName = "list.employees", lowCardinalityKeyValues = {"framework", "spring"})
    public List<EmployeeResource> listEmployees() {
        log.info("List employees 2");

//        return Observation.createNotStarted("controller.hello", observationRegistry)
//                .lowCardinalityKeyValue("framework", "spring")
//                .observe(() -> {
//                    return repository.findAllResources();
//                });


        return repository.findAllResources();
    }

    public EmployeeResource findEmployeeById(long id) {
        return toDto(repository.findById(id).orElseThrow(notFountException(id)));
    }

    public EmployeeResource createEmployee(EmployeeResource command) {
        Employee employee = new Employee(command.getName());
        repository.save(employee);
        return toDto(employee);
    }

    public EmployeeResource updateEmployee(long id, EmployeeResource command) {
        Employee employee = repository.findById(id).orElseThrow(notFountException(id));
        employee.setName(command.getName());
        return toDto(employee);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    private EmployeeResource toDto(Employee employee) {
        return new EmployeeResource(employee.getId(), employee.getName());
    }

    private Supplier<EmployeeNotFoundException> notFountException(long id) {
        return () -> new EmployeeNotFoundException("Employee not found with id: %d".formatted(id));
    }

}
