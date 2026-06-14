package lv.venta.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"lv.venta"})
public class LibrarySystemApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplicationTests.class, args);
    }
}