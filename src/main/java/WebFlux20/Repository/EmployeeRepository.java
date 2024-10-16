package WebFlux20.Repository;

import WebFlux20.Entity.Employee;
import reactor.core.publisher.Mono;


import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee,Long> {

    @Modifying
    @Query("UPDATE employee SET project = :newProject")
    Mono<Void> updateProjectForAllEmployees(String project);
    
    @Modifying
    @Query("UPDATE employee SET project = :newProject WHERE id = :id") // Adjust the SQL to match your schema
    Mono<Void> updateProjectForEmployee(Long id, String newProject);
    

}
