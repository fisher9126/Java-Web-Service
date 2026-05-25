package re.jwsss8bt3.aspect;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import re.jwsss8bt3.annotation.RequireManagerApproval;
import re.jwsss8bt3.annotation.RequireOtp;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect {

    private final HttpServletRequest request;

    @Before("@annotation(requireOtp)")
    public void verifyOtp(RequireOtp requireOtp) {

        String otp = request.getHeader("X-OTP");

        if (otp == null || !otp.equals("123456")) {
            throw new RuntimeException("OTP không hợp lệ");
        }
    }

    @Before("@annotation(requireManagerApproval)")
    public void verifyManagerRole(
            RequireManagerApproval requireManagerApproval
    ) {

        String role = request.getHeader("X-Role");

        if (role == null || !role.equals("MANAGER")) {
            throw new RuntimeException("Bạn không có quyền thực hiện");
        }
    }
}
