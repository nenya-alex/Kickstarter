package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ua.nenya.domain.Payment;
import ua.nenya.dto.PaymentDTO;
import ua.nenya.exceptions.PaymentException;
import ua.nenya.mapper.PaymentMapper;
import ua.nenya.service.PaymentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        log.debug("REST request to create Payment");
        if (paymentDTO.getId() != null){
            throw new PaymentException("Payment cannot be created because not null id, paymentDTO:" + paymentDTO);
        }

        return ResponseEntity.ok().body(paymentService.createPayment(paymentDTO));
    }

}
