package lv.venta.repo;

import lv.venta.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByLibraryDepartmentIdld(long deptId);
}