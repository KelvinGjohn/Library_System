package lv.venta.service;

import lv.venta.model.LibraryDepartment;

import java.util.List;

public interface ILibraryDepartmentService {
    List<LibraryDepartment> selectAll();
    LibraryDepartment selectById(long id) throws Exception;
    void add(LibraryDepartment dept);
    void updateById(long id, LibraryDepartment updated) throws Exception;
    void removeById(long id) throws Exception;
}