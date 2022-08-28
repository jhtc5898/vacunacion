package com.krugger.vacunacion.security;

import com.krugger.vacunacion.entities.Authenticator;
import com.krugger.vacunacion.repository.AuthenticatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    AuthenticatorRepository authenticatorRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected InMemoryUserDetailsManager usersDetailsMemory() throws Exception {

        System.out.println(authenticatorRepository.findAllByStatus(Boolean.TRUE));
        List<UserDetails> users = mappingUser(authenticatorRepository.findAllByStatus(Boolean.TRUE));

//		List<UserDetails> users = List.of(
//				User.withUsername("user1").password("{noop}user1").roles("USER").build(),
//				User.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN").build(),
//				User.withUsername("user2").password("{noop}user2").roles("OPERATOR").build());// lo de {noop} se pone para no obligar a usar mecanismo de encriptación
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        http.csrf().disable().authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/admin/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/addEmployee").hasRole("ADMIN")
                //.antMatchers("/cliente/*").hasAnyRole("ADMIN", "OPERATOR")
                //.antMatchers(HttpMethod.PUT, "/curso").authenticated()
                .antMatchers("/listarP").authenticated()
                .antMatchers("/listarTv/*").authenticated()
                .and().apply(MyCustomDsl.customDsl()); // forma de solicitar los credenciales
        return http.build();
    }

    public List<UserDetails> mappingUser(List<Authenticator> authenticatorList) {
        List<UserDetails> listUserDetails = new ArrayList<>();
        for (Authenticator auth : authenticatorList) {
            listUserDetails.add
                    (
                            User.withUsername(auth.getNickname()).password("{noop}" + auth.getPassword()).roles(auth.getRole().getName()).build()
                    );
        }

        return listUserDetails;


    }

}
