package MyFitnessJourney.VTTP.Project.Fitness;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import MyFitnessJourney.VTTP.Project.Fitness.filters.AuthenticationFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerFilters() {

        AuthenticationFilter authFilter = new AuthenticationFilter();
        FilterRegistrationBean<AuthenticationFilter> regFilter = new FilterRegistrationBean<>();
        regFilter.setFilter(authFilter);
        regFilter.addUrlPatterns("/protected/*");
        regFilter.setOrder(1);

        return regFilter;
    }
    
}
