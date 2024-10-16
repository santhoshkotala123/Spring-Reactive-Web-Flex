package WebFlux20.Service;

import WebFlux20.Entity.Employee;
import WebFlux20.Repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Mono<Employee> createUser(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Flux<Employee> saveAllEmployees(Flux<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Mono<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    public Mono<Void> updateProjectForAllEmployees(String newProject) {
        return employeeRepository.updateProjectForAllEmployees(newProject);
    }
    
    public Mono<Void> updateProjectForEmployee(Long id, String newProject) {
        return employeeRepository.updateProjectForEmployee(id, newProject);
    }
    
    public Mono<Void> updateProjectForEmployees(List<Long> ids, String newProject) {
        return Flux.fromIterable(ids)
                .flatMap(id -> employeeRepository.findById(id)
                        .flatMap(employee -> {
                            employee.setProject(newProject);
                            return employeeRepository.save(employee);
                        }))
                .then(); // Return a Mono<Void> indicating completion
    }

}
