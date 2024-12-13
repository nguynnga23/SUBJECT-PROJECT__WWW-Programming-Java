package com.example.backend.configs;

import com.example.backend.services.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//https://stackoverflow.com/questions/72381114/upgrading-the-deprecated-websecurityconfigureradapter-in-spring-boot-2-7-0
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String CANDIDATE_ROLE = "ROLE_CANDIDATE";
    private static final String COMPANY_ROLE = "ROLE_COMPANY";

    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl; // Tự động tiêm (inject) 1 triển khai của UserDetailsService
    @Autowired
    public SecurityConfiguration(CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
        this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        CustomUserDetails role_candidate = (CustomUserDetails) User.builder().roles(CANDIDATE_ROLE).build();
//        CustomUserDetails role_company = (CustomUserDetails) User.builder().roles(COMPANY_ROLE).build();
//        auth.inMemoryAuthentication().withUser(role_company).withUser(role_candidate);
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //Định nghĩa một bean cho BCryptPasswordEncoder để mã hóa mật khẩu.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //cấu hình chính cho bảo mật web.
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/js/**", "/css/**", "/img/**", "/").permitAll()
                        .requestMatchers("/login", "/register/**").permitAll()
                        .requestMatchers("/candidates/**").hasAuthority(CANDIDATE_ROLE)
                        .requestMatchers("/company/**").hasAuthority(COMPANY_ROLE)
                        .anyRequest().authenticated() // tất cả các yêu cầu khác phải được xác thực
                )
                .formLogin(form -> form
                        .loginPage("/login") //Chỉ định URL của trang đăng nhập và cho phép tất cả người dùng truy cập trang này.
                        .permitAll()
//                        .defaultSuccessUrl("/candidates/profile", true)
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().stream()
                                    .map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
                            if (role.equals(CANDIDATE_ROLE)) {
                                response.sendRedirect(request.getContextPath() + "/candidates/home");
                            }else if (role.equals(COMPANY_ROLE)) {
                                response.sendRedirect(request.getContextPath() + "/companies/home");
                            }else {
                                response.sendRedirect(request.getContextPath() + "/login");
                            }
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsServiceImpl);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
