package re.jwsss8bt3.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import re.jwsss8bt3.dto.PaymentRequestDTO;
import re.jwsss8bt3.dto.RefundRequestDTO;
import re.jwsss8bt3.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/domestic")
    public String domestic(
            @RequestBody @Valid PaymentRequestDTO request
    ) {

        return paymentService.processDomesticPayment(request);
    }

    @PostMapping("/international")
    public String international(
            @RequestBody @Valid PaymentRequestDTO request
    ) {

        return paymentService.processInternationalPayment(request);
    }

    @PostMapping("/refund")
    public String refund(
            @RequestBody @Valid RefundRequestDTO request
    ) {

        return paymentService.processRefund(request);
    }
}