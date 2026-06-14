package lv.venta.repo;

import lv.venta.model.LibraryDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibraryDepartmentRepo extends JpaRepository<LibraryDepartment, Long> {
}