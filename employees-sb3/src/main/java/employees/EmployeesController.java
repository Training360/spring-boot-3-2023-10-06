package employees;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeResource> listEmployees() {
        return employeesService.listEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResource findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResource createEmployee(@Valid @RequestBody EmployeeResource command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeResource updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeResource command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }

}
