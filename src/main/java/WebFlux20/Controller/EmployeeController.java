package WebFlux20.Controller;

import WebFlux20.Entity.Employee;
import WebFlux20.Service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee) {
        return employeeService.createUser(employee);
    }

    @PostMapping("/saveAll")
    public Flux<Employee> saveAllEmployees(@RequestBody Flux<Employee> employees) {
        return employeeService.saveAllEmployees(employees);
    }

    @GetMapping("/get/{id}")
    public Mono<Employee> getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/getAll")
    public Flux<Employee> findAll() {
        return employeeService.getAllUsers();
    }
  
    @PutMapping("/updateProject")
    public Mono<Void> updateProjectForAllEmployees(@RequestParam String newProject) {
        return employeeService.updateProjectForAllEmployees(newProject);
    }
    
    @PutMapping("/updateProject/{id}")
    public Mono<Void> updateProjectForEmployee(@PathVariable Long id, @RequestParam String newProject) {
        return employeeService.updateProjectForEmployee(id, newProject);
    }
    
//    @PutMapping("/updateProject")
//    public Mono<Void> updateProjectForEmployees(@RequestParam List<Long> ids, @RequestParam String newProject) {
//        return employeeService.updateProjectForEmployees(ids, newProject);
//    }

}
