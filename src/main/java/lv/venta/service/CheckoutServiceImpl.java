package lv.venta.service;

import lv.venta.model.Book;
import lv.venta.model.Checkout;
import lv.venta.model.Reader;
import lv.venta.repo.IBookRepo;
import lv.venta.repo.ICheckoutRepo;
import lv.venta.repo.IReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    private ICheckoutRepo checkoutRepo;

    @Autowired
    private IReaderRepo readerRepo;

    @Autowired
    private IBookRepo bookRepo;

    @Override
    public List<Checkout> selectAll() {
        return checkoutRepo.findAll();
    }

    @Override
    public Checkout selectById(long id) throws Exception {
        return checkoutRepo.findById(id)
                .orElseThrow(() -> new Exception("Checkout with id " + id + " not found"));
    }

    @Override
    public void checkoutBook(long readerId, long bookId) throws Exception {
        Reader reader = readerRepo.findById(readerId)
                .orElseThrow(() -> new Exception("Reader not found"));
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found"));

        Checkout checkout = new Checkout(
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                false,
                reader,
                book
        );
        checkoutRepo.save(checkout);
    }

    @Override
    public void returnBook(long checkoutId) throws Exception {
        Checkout checkout = selectById(checkoutId);
        checkout.setReturned(true);
        checkoutRepo.save(checkout);
    }

    @Override
    public List<Checkout> selectActiveCheckouts() {
        return checkoutRepo.findByReturnedFalse();
    }
}