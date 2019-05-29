package leo.henry.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public MapReactiveUserDetailsService authentication(){
        UserDetails henry = User.withDefaultPasswordEncoder()
                .username("henry").password("password").roles("USER","ADMIN").build();

        UserDetails jeo = User.withDefaultPasswordEncoder()
                .username("jeo").password("anotherPassword").roles("USER").build();
        return new MapReactiveUserDetailsService(henry,jeo);

    }
    @Bean
    public SecurityWebFilterChain authorixation(ServerHttpSecurity httpSecurity){
        return httpSecurity
                .httpBasic().
                        and()
                .authorizeExchange()
                .anyExchange()
                .hasRole("ADMIN")
                .and()
                .build();
    }
}
