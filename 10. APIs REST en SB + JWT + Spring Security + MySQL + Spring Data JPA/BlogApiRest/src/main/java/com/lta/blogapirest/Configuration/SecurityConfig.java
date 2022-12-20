package com.lta.blogapirest.Configuration;

import com.lta.blogapirest.Security.CustomUserDetailsService;
import com.lta.blogapirest.Security.JwtAuthenticationEntryPoint;
import com.lta.blogapirest.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity  //Autorizacion para crear clase de seguridad personalizada.
@EnableGlobalMethodSecurity(prePostEnabled = true)//
public class SecurityConfig extends WebSecurityConfigurerAdapter {      //extendemos y redefinimos algunos de
    @Autowired                                                          //los metodos de la clase padre.
    private CustomUserDetailsService cuds;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAEP;


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
/*
    @Override   //Permitimos Gets libres, lo demas se authentica.
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()        //spring por si mismo ya posee csrf
               .authorizeRequests().antMatchers(HttpMethod.GET,"/api/**")
               .permitAll()
               .anyRequest()
               .authenticated().and().httpBasic();
    }
*/
    @Override   //Permitimos Gets libres, lo demas se authentica.
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()        //spring por si mismo ya posee csrf
                .exceptionHandling()
                .authenticationEntryPoint(jwtAEP)   //Excepciones jwt
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Sesiones
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/**")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override   //Utiliza el cuds para validar la autenticacion.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cuds).passwordEncoder(passwordEncoder());
    }

    /*Sirve para carga de autoridades en memoria.
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder().username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER").build();
        UserDetails admin = User.builder().username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN").build();
    return new InMemoryUserDetailsManager(user,admin);
    }
*/

}
