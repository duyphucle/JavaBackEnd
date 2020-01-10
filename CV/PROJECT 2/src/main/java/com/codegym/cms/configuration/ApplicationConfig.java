package com.codegym.cms.configuration;

import com.codegym.cms.formatter.*;
import com.codegym.cms.service.*;
import com.codegym.cms.service.impl.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories("com.codegym.cms.repository")
@ComponentScan("com.codegym.cms")
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Thymeleaf Configuration
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html; charset=utf-8");
        return viewResolver;
    }


    //JPA configuration
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.codegym.cms.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test5?useUnicode=true&characterEncoding=UTF-8" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.useUnicode","true");
        properties.setProperty("hibernate.connection.characterEncoding","UTF-8");
        properties.setProperty("hibernate.connection.charSet","UTF-8");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CauHoiFormatter(applicationContext.getBean(CauHoiService.class)));
        registry.addFormatter(new DapAnFormatter(applicationContext.getBean(DapAnService.class)));
        registry.addFormatter(new DatHangFormatter(applicationContext.getBean(DatHangService.class)));
        registry.addFormatter(new DatHangChiTietFormatter(applicationContext.getBean(DatHangChiTietService.class)));
        registry.addFormatter(new HanhChinhFormatter(applicationContext.getBean(HanhChinhService.class)));
        registry.addFormatter(new HuyenFormatter(applicationContext.getBean(HuyenService.class)));
        registry.addFormatter(new LoaiFormatter(applicationContext.getBean(LoaiService.class)));
        registry.addFormatter(new LoaiSanPhamFormatter(applicationContext.getBean(LoaiSanPhamService.class)));
        registry.addFormatter(new NamFormatter(applicationContext.getBean(NamService.class)));
        registry.addFormatter(new NgayFormatter(applicationContext.getBean(NgayService.class)));
        registry.addFormatter(new NhaCungCapFormatter(applicationContext.getBean(NhaCungCapService.class)));
        registry.addFormatter(new NhapKhoFormatter(applicationContext.getBean(NhapKhoService.class)));
        registry.addFormatter(new PhuongFormatter(applicationContext.getBean(PhuongService.class)));
        registry.addFormatter(new SanPhamFormatter(applicationContext.getBean(SanPhamService.class)));
        registry.addFormatter(new SellFormatter(applicationContext.getBean(SellService.class)));
        registry.addFormatter(new SellTheoSanPhamFormatter(applicationContext.getBean(SellTheoSanPhamService.class)));
        registry.addFormatter(new SurveyFormatter(applicationContext.getBean(SurveyService.class)));
        registry.addFormatter(new ThangFormatter(applicationContext.getBean(ThangService.class)));
        registry.addFormatter(new ThanhPhoFormatter(applicationContext.getBean(ThanhPhoService.class)));
        registry.addFormatter(new ThongTinFormatter(applicationContext.getBean(ThongTinService.class)));
        registry.addFormatter(new TraLoiFormatter(applicationContext.getBean(TraLoiService.class)));
        registry.addFormatter(new TraLoiChiTietFormatter(applicationContext.getBean(TraLoiChiTietService.class)));
        registry.addFormatter(new UserFormatter(applicationContext.getBean(UserService.class)));
        registry.addFormatter(new UserDoingSurveyFormatter(applicationContext.getBean(UserDoingSurveyService.class)));
        registry.addFormatter(new XeFormatter(applicationContext.getBean(XeService.class)));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/sb2/**")
                .addResourceLocations("/sb2/").resourceChain(false);

        registry.addResourceHandler("/ithost/**")
                .addResourceLocations("/ithost/").resourceChain(false);

        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("/upload/").resourceChain(false);

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/").resourceChain(false);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CauHoiService cauhoiService() {
        return new CauHoiServiceImpl();
    }

    @Bean
    public DapAnService dapanService() {
        return new DapAnServiceImpl();
    }

    @Bean
    public DatHangService dathangService() {
        return new DatHangServiceImpl();
    }

    @Bean
    public DatHangChiTietService dathangchitietService() {
        return new DatHangChiTietServiceImpl();
    }

    @Bean
    public HanhChinhService hanhchinhService() {
        return new HanhChinhServiceImpl();
    }

    @Bean
    public HuyenService huyenService() {
        return new HuyenServiceImpl();
    }

    @Bean
    public LoaiService loaiService() {
        return new LoaiServiceImpl();
    }

    @Bean
    public LoaiSanPhamService loaisanphamService() {
        return new LoaiSanPhamServiceImpl();
    }

    @Bean
    public NamService namService() {
        return new NamServiceImpl();
    }

    @Bean
    public NgayService ngayService() {
        return new NgayServiceImpl();
    }

    @Bean
    public NhaCungCapService nhacungcapService() {
        return new NhaCungCapServiceImpl();
    }

    @Bean
    public NhapKhoService nhapkhoService() {
        return new NhapKhoServiceImpl();
    }

    @Bean
    public PhuongService phuongService() {
        return new PhuongServiceImpl();
    }

    @Bean
    public SanPhamService sanphamService() {
        return new SanPhamServiceImpl();
    }

    @Bean
    public SellService sellService() {
        return new SellServiceImpl();
    }

    @Bean
    public SellTheoSanPhamService selltheosanphamService() {
        return new SellTheoSanPhamServiceImpl();
    }

    @Bean
    public SurveyService surveyService() {
        return new SurveyServiceImpl();
    }

    @Bean
    public ThangService thangService() {
        return new ThangServiceImpl();
    }

    @Bean
    public ThanhPhoService thanhphoService() {
        return new ThanhPhoServiceImpl();
    }

    @Bean
    public ThongTinService thongtinService() {
        return new ThongTinServiceImpl();
    }

    @Bean
    public TraLoiService traloiService() {
        return new TraLoiServiceImpl();
    }

    @Bean
    public TraLoiChiTietService traloichitietService() {
        return new TraLoiChiTietServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDoingSurveyService userdoingsurveyService() {
        return new UserDoingSurveyServiceImpl();
    }

    @Bean
    public XeService xeService() {
        return new XeServiceImpl();
    }
}
