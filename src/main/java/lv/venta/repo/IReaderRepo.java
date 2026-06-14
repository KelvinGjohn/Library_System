package lv.venta.repo;

import lv.venta.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReaderRepo extends JpaRepository<Reader, Long> {
    Optional<Reader> findByLibraryUserNumber(String libraryUserNumber);
}