package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.PaymentRequestDTO;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Entity.Payment;
import backend.order_spring_designpatterns.Repository.PaymentRepository;
import backend.order_spring_designpatterns.Service.Enums.StatusPaymentEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment insert(PaymentRequestDTO paymentRequestDTO, Order order) {
        Payment payment = new Payment();

        /* Uma vez que "order" corresponde ao objeto passado como parâmetro, sendo uma referência ao Order recebido de
        OrderService.insert(), o campo order_id da tabela OrderItem será preenchido pelo JPA com o id gerado para tal
        Order durante sua criação. Isso ocorre porque houve declaração de relacionamente entre as entidades/campos */
        payment.setOrder(order);
        payment.setPaymentDate(OffsetDateTime.now());
        payment.setType(paymentRequestDTO.type());
        payment.setStatus(StatusPaymentEnum.PENDING);

        paymentRepository.save(payment);
        return payment;
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public void updateById(Long id, PaymentRequestDTO paymentRequest){
        Payment payment = findById(id);
        payment.setType(paymentRequest.type());
        paymentRepository.save(payment);
    }

    public void updatePaid(Long id){
        Payment payment = findById(id);
        payment.setStatus(StatusPaymentEnum.APPROVED);
        paymentRepository.save(payment);
    }
}
