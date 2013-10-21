package net.canang.cfi.web.config;

import net.canang.cfi.biz.integration.birt.BirtEngineFactory;
import net.canang.cfi.biz.integration.birt.BirtView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@EnableWebMvc
@ComponentScan( {"net.canang.cfi.biz.integration.birt"})
@Configuration
public class CfWebBirtConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/reports").setViewName("birtView");

    }

    @Bean
    public BirtView birtView(){
        BirtView bv = new BirtView();
        bv.setBirtEngine( this.engine().getObject() );
        return bv;
    }


    @Bean public BeanNameViewResolver beanNameResolver(){
        BeanNameViewResolver br = new BeanNameViewResolver() ;
        return br;
    }

    @Bean
    protected BirtEngineFactory engine(){
        BirtEngineFactory factory = new BirtEngineFactory() ;
        //Enable BIRT Engine Logging
        //factory.setLogLevel( Level.FINEST);
        //factory.setLogDirectory( new FileSystemResource("c:/temp"));
        return factory ;
    }
}
