package lv.venta.repo;

import lv.venta.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICheckoutRepo extends JpaRepository<Checkout, Long> {
    List<Checkout> findByReturnedFalse();
    List<Checkout> findByReaderIdr(long readerId);
}