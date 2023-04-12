package com.project;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	

	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
             
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found"));
            }
        };
    }
 	
 	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 	
 	@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
 	
 	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         
        http.authorizeRequests()
        .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
        .antMatchers("/auth/login").permitAll()
        .anyRequest().authenticated();
         
            http.exceptionHandling()
                    .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                ex.getMessage()
                            );
                        }
                );
         
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
         
        return http.build();
    }
}
