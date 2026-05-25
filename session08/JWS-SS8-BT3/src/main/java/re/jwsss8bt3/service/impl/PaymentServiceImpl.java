package re.jwsss8bt3.service.impl;


import org.springframework.stereotype.Service;
import re.jwsss8bt3.annotation.RequireManagerApproval;
import re.jwsss8bt3.annotation.RequireOtp;
import re.jwsss8bt3.dto.PaymentRequestDTO;
import re.jwsss8bt3.dto.RefundRequestDTO;
import re.jwsss8bt3.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String processDomesticPayment(PaymentRequestDTO request) {

        return "Domestic payment success";
    }

    @Override
    @RequireOtp
    public String processInternationalPayment(PaymentRequestDTO request) {

        return "International payment success";
    }

    @Override
    @RequireManagerApproval
    public String processRefund(RefundRequestDTO request) {

        return "Refund success";
    }
}
