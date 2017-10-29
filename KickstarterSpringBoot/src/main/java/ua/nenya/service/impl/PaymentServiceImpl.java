package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Payment;
import ua.nenya.dto.PaymentDTO;
import ua.nenya.mapper.PaymentMapper;
import ua.nenya.repository.PaymentRepository;
import ua.nenya.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentMapper paymentMapper;

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentMapper paymentMapper,
                              PaymentRepository paymentRepository) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        log.debug("Request to create Payment");
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDTO);
        Payment result = paymentRepository.saveAndFlush(payment);
        return paymentMapper.paymentToPaymentDTO(result);
    }
}
