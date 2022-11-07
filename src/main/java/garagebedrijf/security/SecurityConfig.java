package garagebedrijf.security;

import garagebedrijf.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/customers").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/customers").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/customers/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.PUT, "/customers/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.DELETE, "/customers/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.POST, "/cars").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/cars").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET,"/cars/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.PUT, "/cars/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.DELETE, "/cars/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.PUT, "/cars/{carId}/{customerId}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.POST, "/parts").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/parts").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/parts/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.PUT, "/parts/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.DELETE, "/parts/{id}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.POST, "/invoices").hasAuthority("MONTEUR")
                .antMatchers(HttpMethod.GET, "/invoices").hasAuthority("KASSA MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/invoices/{id}").hasAuthority("KASSA MEDEWERKER")
                .antMatchers(HttpMethod.PUT, "/invoices/{id}").hasAuthority("MONTEUR")
                .antMatchers(HttpMethod.DELETE, "/invoices/{id}").hasAuthority("MONTEUR")
                .antMatchers(HttpMethod.POST, "/repairs").hasAuthority("MONTEUR")
                .antMatchers(HttpMethod.PUT, "/repairs/{id}").hasAuthority("MONTEUR")
                .antMatchers(HttpMethod.POST, "/single/uploadDb").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .antMatchers(HttpMethod.GET, "/downloadFromDB/{fileName}").hasAuthority("ADMINISTRATIEF MEDEWERKER")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
