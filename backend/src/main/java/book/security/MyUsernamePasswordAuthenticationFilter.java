package book.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import book.service.form.auth.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // "/api/login" の場合に認証を行うよう設定
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

        // 成功した場合の処理
        this.setAuthenticationSuccessHandler((req, res, ex) -> {
            // SecurityContextの永続化
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), req, res);
            res.setStatus(200);
            MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            res.getWriter().write((new ObjectMapper()).writeValueAsString(user.getAccount()));
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // リクエストのデータを LoginForm として取り出す
            LoginForm principal = new ObjectMapper().readValue(request.getInputStream(),
                    LoginForm.class);
            // 認証処理を実行する
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(principal.getUserId(),
                            principal.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
