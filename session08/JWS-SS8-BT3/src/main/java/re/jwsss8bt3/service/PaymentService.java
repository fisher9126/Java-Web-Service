package re.jwsss8bt3.service;


import re.jwsss8bt3.dto.PaymentRequestDTO;
import re.jwsss8bt3.dto.RefundRequestDTO;

public interface PaymentService {

    String processDomesticPayment(
            PaymentRequestDTO request);

    String processInternationalPayment(PaymentRequestDTO request);

    String processRefund(RefundRequestDTO request);
}
