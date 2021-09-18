package pl.coderslab.app;

import java.util.Locale;
import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("pl.coderslab.app")
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

  @Bean
  public LocalEntityManagerFactoryBean entityManagerFactory() {
    LocalEntityManagerFactoryBean entityManagerFactoryBean = new LocalEntityManagerFactoryBean();
    entityManagerFactoryBean.setPersistenceUnitName("tomasz");
    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
    return jpaTransactionManager;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Bean(name="localeResolver")
  public LocaleContextResolver getLocaleContextResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("pl","PL"));
    return localeResolver;
  }

  @Bean
  public Validator validator() {
    return new LocalValidatorFactoryBean();
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(publisherConverter());
  }

  @Bean
  public PublisherConverter publisherConverter() {
    return new PublisherConverter();
  }

}
