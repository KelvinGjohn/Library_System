package lv.venta.service;

import lv.venta.model.Checkout;
import lv.venta.model.Reader;
import lv.venta.repo.IReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements IReaderService {

    @Autowired
    private IReaderRepo readerRepo;

    @Override
    public List<Reader> selectAll() {
        return readerRepo.findAll();
    }

    @Override
    public Reader selectById(long id) throws Exception {
        return readerRepo.findById(id)
                .orElseThrow(() -> new Exception("Reader with id " + id + " not found"));
    }

    @Override
    public void add(Reader reader) {
        readerRepo.save(reader);
    }

    @Override
    public void updateById(long id, Reader updatedReader) throws Exception {
        Reader existing = selectById(id);
        existing.setName(updatedReader.getName());
        existing.setSurname(updatedReader.getSurname());
        existing.setLibraryUserNumber(updatedReader.getLibraryUserNumber());
        readerRepo.save(existing);
    }

    @Override
    public void removeById(long id) throws Exception {
        Reader existing = selectById(id);
        readerRepo.delete(existing);
    }

    @Override
    public List<Checkout> loadHistory(long readerId) throws Exception {
        Reader reader = selectById(readerId);
        return reader.loadHistory();
    }

    @Override
    public List<Checkout> currentTakenBooks(long readerId) throws Exception {
        Reader reader = selectById(readerId);
        return reader.getCurrentTakenBookList();
    }
}