package com.shopnobazz.blooddoante.cofigaretion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.shopnobazz.blooddoante.serviceImplement.UserSecurityService;
import com.shopnobazz.blooddoante.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
@Autowired
UserSecurityService userSecurityService;

private BCryptPasswordEncoder passwordEncoder() {
    return SecurityUtility.passwordEncoder();
}


	 private static final String[] PUBLIC_MATCHERS = {
	            "/css/**",
	            "/js/**",
	            "/images/**",
	            "/home/**",
	            "/newUser",
	            "/forgetPassword",
	            "/login",
	            "/fonts/**",
	            "/hours",
	            "/faq",
	            "/register",
	            "/",
	            "/confirm-account"
	            
	            
	            
	            
	            
	    };
	@Override
    protected void configure(HttpSecurity http) throws Exception {
       
		  http
          .authorizeRequests()
          .antMatchers(PUBLIC_MATCHERS)
          .permitAll()
          .antMatchers("/newUser").permitAll()
          .antMatchers("/forgetPassword").permitAll()
          
          .antMatchers("/home/**").hasAuthority("ROLE_ADMIN")
          .antMatchers("/profile/**","/member/**").hasAuthority("ROLE_USER")
          .anyRequest()
          .authenticated();

  http
          .csrf().disable().cors().disable()
          
          .formLogin().failureUrl("/login?error")
          .loginPage("/login").permitAll()
          .defaultSuccessUrl("/")
          .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
          .and()
          .rememberMe();
                
    }
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService);
    }
}
