package com.belen.torneo.config;

import com.belen.torneo.jwt.JwtEntryPoint;
import com.belen.torneo.jwt.JwtTokenFilter;
import com.belen.torneo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.security.config.http.SessionCreationPolicy;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableSwagger2
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter authenticationJwtTokenFilter() {
        return new JwtTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(source);
        /*http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/fa-solid-900.woff2").permitAll()
                .antMatchers("/api/equiposDeportistas/listarDeportistaAnotaciones").permitAll()
                .antMatchers("/api/equiposDeportistas/listarVallaMenosVencida").permitAll()
                .antMatchers("/api/equiposDeportistas/listarPosicionEquipos").permitAll()
                .antMatchers("/api/campeonatos/listarCampeonato").permitAll()
                .antMatchers("/api/campeonatos/listar").permitAll()
                .antMatchers("/api/usuarios/signin").permitAll()
                .antMatchers("/api/equipos/listarPosicionEquipo").permitAll()
                .antMatchers("/api/equipos/listarPosicionEquipos").permitAll()
                .antMatchers("/api/usuarios/listarDelegados").permitAll()
                .antMatchers("/api/equipos/listarEquipoByTorneo").permitAll()
                .antMatchers("/api/grupos/listarGrupo").permitAll()
                .antMatchers("/api/calendarios/listar").permitAll()
                .antMatchers("/api/usuarios/crearAdmin").permitAll()
                .antMatchers("/api/usuarios/listarUsuariosAdmin").permitAll()
                .antMatchers("/api/usuarios/deleteAdmin/{id}").permitAll()
                .antMatchers("/api/grupos/listarGrupoByTorneo").permitAll()
                .antMatchers("/api/calendarios/listarByCampeonato").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers("/api/datos/listarDatosByCategoria").permitAll()
                .antMatchers("/api/campeonatos").permitAll()
                .anyRequest().authenticated();*/
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().permitAll();
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Campeonatos Deportivos de Belén Nariño") // Cambia este título por el que desees
                .description("Plataforma Web encargada de gestionar los campeonatos deportivos de las disciplinas de " +
                        "fútbol, micro fútbol y baloncesto realizados por la Alcaldía municipal de Belén Nariño")
                .version("1.0")
                .build();
    }

}
