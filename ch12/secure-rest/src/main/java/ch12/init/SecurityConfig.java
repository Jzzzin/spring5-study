package ch12.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.inMemoryAuthentication()
                    .withUser("prospring5")
//                    .password("prospring5") // 패스워드 인코딩 시 패스워드 앞에 ID가 자동으로 붙기 때문에 ID를 붙게 하지 않으려면 {noop}을 추가함
                    .password("{noop}prospring5")
                    .roles("REMOTE");
        } catch (Exception e) {
            logger.error("인증을 구성할 수 없음!", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
//                    .antMatchers("/**").permitAll() // 모든 접속이 허용됨
                    .antMatchers("/*", "/styles/*").permitAll() // root 디렉토리와 styles 디렉토리 내의 접속만 허용함
                    .antMatchers("/rest/**").hasRole("REMOTE").anyRequest().authenticated()
                    .and()
                .formLogin()
                    .and()
                .httpBasic()
                    .and()
                .csrf().disable();
    }
}
