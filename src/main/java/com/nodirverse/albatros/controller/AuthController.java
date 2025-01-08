package com.nodirverse.albatros.controller;


import com.nodirverse.albatros.dto.request.AuthRequest;
import com.nodirverse.albatros.dto.request.SignUpRequest;
import com.nodirverse.albatros.dto.request.TokenRefreshRequest;
import com.nodirverse.albatros.dto.request.VerificationRequest;
import com.nodirverse.albatros.dto.response.ApiResponse;
import com.nodirverse.albatros.dto.response.JwtResponse;
import com.nodirverse.albatros.service.UserService;
import com.nodirverse.albatros.service.jwt.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final LoginAttemptService loginAttemptService;
    private final HttpServletRequest request;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("User successfully registered")
                .data(userService.signUp(request))
                .build());
    }

    @PostMapping("/verification")
    public ResponseEntity<ApiResponse<JwtResponse>> verification(@Valid @RequestBody VerificationRequest request) {
        return ResponseEntity.ok(ApiResponse.<JwtResponse>builder()
                .success(true)
                .message("Verification successful")
                .data(userService.verification(request))
                .build());
    }


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<JwtResponse>> signIn(@Valid @RequestBody AuthRequest request) {
        String clientIp = getClientIP(this.request);

        if (loginAttemptService.isBlocked(clientIp)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(ApiResponse.<JwtResponse>builder()
                            .success(false)
                            .message("Too many attempts. You are temporarily blocked!")
                            .data(null)
                            .build());
        }

        JwtResponse response = userService.signIn(request, clientIp);
        return ResponseEntity.ok(ApiResponse.<JwtResponse>builder()
                .success(true)
                .message("Successfully signed in")
                .data(response)
                .build());
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<JwtResponse>> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(ApiResponse.<JwtResponse>builder()
                .success(true)
                .message("Token refreshed successfully")
                .data(userService.tokenRefresh(request))
                .build());
    }
}
