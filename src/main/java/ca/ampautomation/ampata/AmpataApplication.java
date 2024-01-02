package ca.ampautomation.ampata;

import com.google.common.base.Strings;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@Push
@Theme(value = "Ampata")
@PWA(name = "Ampata", shortName = "Ampata")
@SpringBootApplication
public class AmpataApplication implements AppShellConfigurator {

    @Autowired
    private Environment environment;

    Logger logger = LoggerFactory.getLogger(AmpataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AmpataApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        String logPrfx = "dataSource";
        logger.trace(logPrfx + " --> ");


        logger.debug(logPrfx + " --- dataSourceProperties.getName(): " + dataSourceProperties.getName());
        logger.debug(logPrfx + " --- dataSourceProperties.getUrl(): " + dataSourceProperties.getUrl());
        logger.debug(logPrfx + " --- dataSourceProperties.getDriverClassName(): " + dataSourceProperties.getDriverClassName());
        logger.debug(logPrfx + " --- dataSourceProperties.getUsername(): " + dataSourceProperties.getUsername());

        DataSourceBuilder<?> dsb = dataSourceProperties.initializeDataSourceBuilder();

        logger.trace(logPrfx + " <-- ");
        return dsb.build();
    }

    @EventListener
    public void printApplicationUrl(final ApplicationStartedEvent event) {
        LoggerFactory.getLogger(AmpataApplication.class).info("Application started at "
                + "http://localhost:"
                + environment.getProperty("local.server.port")
                + Strings.nullToEmpty(environment.getProperty("server.servlet.context-path")));
    }

}
