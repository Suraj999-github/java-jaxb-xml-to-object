package crud.demo.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crud.demo.employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
