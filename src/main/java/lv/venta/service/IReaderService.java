package lv.venta.service;

import lv.venta.model.Checkout;
import lv.venta.model.Reader;

import java.util.List;

public interface IReaderService {
    // CRUD
    List<Reader> selectAll();
    Reader selectById(long id) throws Exception;
    void add(Reader reader);
    void updateById(long id, Reader updatedReader) throws Exception;
    void removeById(long id) throws Exception;

    // loadHistory for a reader
    List<Checkout> loadHistory(long readerId) throws Exception;

    // Current taken books
    List<Checkout> currentTakenBooks(long readerId) throws Exception;
}