package ua.nenya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
