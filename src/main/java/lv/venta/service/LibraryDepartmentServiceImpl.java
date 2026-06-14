package lv.venta.service;

import lv.venta.model.LibraryDepartment;
import lv.venta.repo.ILibraryDepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryDepartmentServiceImpl implements ILibraryDepartmentService {

    @Autowired
    private ILibraryDepartmentRepo deptRepo;

    @Override
    public List<LibraryDepartment> selectAll() {
        return deptRepo.findAll();
    }

    @Override
    public LibraryDepartment selectById(long id) throws Exception {
        return deptRepo.findById(id)
                .orElseThrow(() -> new Exception("Department with id " + id + " not found"));
    }

    @Override
    public void add(LibraryDepartment dept) {
        deptRepo.save(dept);
    }

    @Override
    public void updateById(long id, LibraryDepartment updated) throws Exception {
        LibraryDepartment existing = selectById(id);
        existing.setSpecialization(updated.getSpecialization());
        existing.setEmployees(updated.getEmployees());
        existing.setWorkingTimes(updated.getWorkingTimes());
        deptRepo.save(existing);
    }

    @Override
    public void removeById(long id) throws Exception {
        LibraryDepartment existing = selectById(id);
        deptRepo.delete(existing);
    }
}