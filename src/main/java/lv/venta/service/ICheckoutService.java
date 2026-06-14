package lv.venta.service;

import lv.venta.model.Checkout;

import java.util.List;

public interface ICheckoutService {
    List<Checkout> selectAll();
    Checkout selectById(long id) throws Exception;
    void checkoutBook(long readerId, long bookId) throws Exception;
    void returnBook(long checkoutId) throws Exception;
    List<Checkout> selectActiveCheckouts();
}