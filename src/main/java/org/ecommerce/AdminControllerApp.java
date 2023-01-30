package org.ecommerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.ecommerce.config.AppWebAppInitializer;
import org.ecommerce.config.mahout.RecommenderConfig;
import org.ecommerce.config.persistence.PersistenceConfig;
import org.ecommerce.config.root.RootConfig;
import org.ecommerce.config.security.SecurityConfig;
import org.ecommerce.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
//@ImportResource({"classpath*:checkout-flow.xml"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,XADataSourceAutoConfiguration.class})
//@ComponentScan({ "org.ecommerce.config.security,org.ecommerce.config.persistence,org.ecommerce.config,org.ecommerce.config.mahout,org.ecommerce.config.root" } )// If our Controller class or Service class is not in the same packages we have //to add packages's name like this...directory(package) with main class
//@ComponentScan({ "org.ecommerce.config.security.SecurityConfig" } )// If our Controller class or Service class is not in the same packages we have //to add packages's name like this...directory(package) with main class
//@ComponentScan(basePackages = {"org.ecommerce.admincontroller"})
//@ComponentScan({"org.ecommerce.web.admin.exceptions,org.ecommerce.web,org.ecommerce.config,org.ecommerce.persistence,org.ecommerce.web.admin.controllers.rest,org.ecommerce.web.services,org.ecommerce.web.services.impl,org.ecommerce.persistence.repositories,org.ecommerce.config.root"})
//@EntityScan("org.ecommerce.persistence.repositories")
//@EntityScan(basePackageClasses = org.ecommerce.persistence.repositories.ProductRepository.class)
////@EnableJpaRepositories(basePackageClasses = 
//{org.ecommerce.persistence.repositories.ProductRepository.class})
/*@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, transactionManagerRef = "transactionManager", basePackages = "org.ecommerce.persistence.repositories", basePackageClasses = 
{org.ecommerce.persistence.repositories.ProductRepository.class,
		org.ecommerce.persistence.repositories.AvatarRepository.class,
		org.ecommerce.persistence.repositories.RememberMeTokenRepository.class,
		org.ecommerce.persistence.repositories.AddressRepository.class,
		org.ecommerce.persistence.repositories.AuthorityRepository.class,
		org.ecommerce.persistence.repositories.AvatarRepository.class,
		org.ecommerce.persistence.repositories.CountryRepository.class,
		org.ecommerce.persistence.repositories.OrderRepository.class,
		org.ecommerce.persistence.repositories.ProductCategoryRepository.class,
		org.ecommerce.persistence.repositories.ProductLineRepository.class,
		org.ecommerce.persistence.repositories.ProductRepository.class,
		org.ecommerce.persistence.repositories.RememberMeTokenRepository.class,
		org.ecommerce.persistence.repositories.ReviewRepository.class,
		org.ecommerce.persistence.repositories.SearchableRepository.class,
		org.ecommerce.persistence.repositories.TasteBoolPreferencesRepository.class,
		org.ecommerce.persistence.repositories.TastePreferencesRepository.class,
		org.ecommerce.persistence.repositories.UserRepository.class})*/
//commenting to check whether will itwork after removing seciruty config
//@Import(value = {StaticResourceConfiguration.class , PersistenceConfig.class, SecurityConfig.class, RecommenderConfig.class })
@Import(value = {StaticResourceConfiguration.class , PersistenceConfig.class })

//@Import(value = { AppWebAppInitializer.class })
//@WebServlet
//WebApplicationInitializer
//@Import(value = { PersistenceConfig.class })
//@Import(value = { StaticResourceConfiguration.class })//

//@EnableWebMvc
//@Import(value={AppWebAppInitializer.class})
//org.ecommerce.web.admin.controllers
@ComponentScan(basePackages={"org.ecommerce.web.app,org.ecommerce.persistence.repositories"})

@EnableTransactionManagement

@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, transactionManagerRef = "transactionManager", basePackages = "org.ecommerce.persistence.repositories")

//@ComponentScan(basePackages={"org.ecommerce.web.app,org.ecommerce.persistence.repositories"})
//@ComponentScan(basePackages={"org.ecommerce.web.repositories"})
public class AdminControllerApp  extends SpringBootServletInitializer {
    public static void main(String[] args)
    {
    	//ApplicationContext ctx = SpringApplication.run(AdminControllerApp.class, args);
		//ctx.getBean("productRepository", ProductRepository.class);
      SpringApplication.run(AdminControllerApp.class, args);
    }
    
   /* public class MyDemoBean implements ApplicationListener<ApplicationStartedEvent> {

        private ServletContext servletContext;

        @Override
        public void onApplicationEvent(ApplicationStartedEvent event) {
            ApplicationContext applicationContext = event.getApplicationContext();
            this.servletContext = ((WebApplicationContext) applicationContext).getServletContext();
    		servletContext.setInitParameter("spring.profiles.active", "production");
        }

    }*/
 /* @Bean
    public ServletRegistrationBean dispatcherServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new DispatcherServlet(), "/");
        registration.setAsyncSupported(true);
        return registration;
    }*/
  /*  @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
       requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }*/
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      //  resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }
   @Bean
   public DispatcherServletRegistrationBean dispatcherServletRegistrationBean() {
       return new DispatcherServletRegistrationBean(dispatcherServlet(), "/");
   }
   
  
  
   @Bean
   public DispatcherServlet dispatcherServlet() {
       return new DispatcherServlet();
   }

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       return builder.sources(AdminControllerApp.class);
   }

  /** @Bean
   public FilterRegistrationBean encodingFilter() {
       CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
       FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
       filterRegBean.setUrlPatterns(getRootPathUrls());
       filterRegBean.setFilter(encodingFilter);
       filterRegBean.setOrder(1);
       return filterRegBean;
   }*/

   private List<String> getRootPathUrls() {
       List<String> urlPatterns = new ArrayList<String>();
       urlPatterns.add("/*");
       return urlPatterns;
   }
   @Bean
   public ServletRegistrationBean dispatcherServletRegistration() {
       final ServletRegistrationBean registration = new ServletRegistrationBean<>(dispatcherServlet(), "/api/*");

       final Map<String, String> params = new HashMap<String, String>();
       params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
       params.put("contextConfigLocation", "org.spring.sec2.spring");
       params.put("dispatchOptionsRequest", "true");
       registration.setInitParameters(params);

       registration.setLoadOnStartup(1);
       return registration;
   }


   /* @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } */
   /* @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/
  
   @Bean
   public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
       return args -> {

           System.out.println("Let's inspect the beans provided by Spring Boot:");

           String[] beanNames = ctx.getBeanDefinitionNames();
           Arrays.sort(beanNames);
           for (String beanName : beanNames) {
               System.out.println(beanName);
           }

       };
   }
}

