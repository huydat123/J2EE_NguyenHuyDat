package com.example.Bai6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. Ai cũng có thể vào trang Login
                        .requestMatchers("/login", "/css/**").permitAll()

                        // 2. USER và ADMIN đều xem được danh sách sản phẩm
                        .requestMatchers("/products").hasAnyRole("USER", "ADMIN")

                        // 3. Chỉ ADMIN mới được thêm sản phẩm (khớp với hình bạn gửi)
                        .requestMatchers("/products/add").hasRole("ADMIN")

                        // 4. Chỉ USER mới được đặt hàng
                        .requestMatchers("/order").hasRole("USER")

                        // Tất cả các trang khác đều phải đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/home", true) // Đăng nhập xong tự vào /home
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handler -> handler
                        .accessDeniedPage("/403") // Trang báo lỗi khi vào sai quyền
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

