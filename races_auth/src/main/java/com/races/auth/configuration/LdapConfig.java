package com.races.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Ldap Config class. Set the Ldap connect configuration
 * @author Maximino Mañanes Ruiz
 */
@Configuration
@ComponentScan(basePackages = {"com.baeldung.ldap.*"})
@Profile("default")
@EnableLdapRepositories(basePackages = "com.races.auth.**")
public class LdapConfig {

    @Autowired
    private Environment env;

    /**
     * Set Context Ldap properties from config.properties file.
     * @return
     */
    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(env.getRequiredProperty("races.auth.ldap.url"));
        contextSource.setBase(env.getRequiredProperty("races.auth.ldap.partitionSuffix"));
        contextSource.setUserDn(env.getRequiredProperty("races.auth.ldap.principal"));
        contextSource.setPassword(env.getRequiredProperty("races.auth.ldap.password"));
        return contextSource;
    }

    /**
     * Bean of ldapTemplate with contextSource.
     * @return
     */
    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }

}
