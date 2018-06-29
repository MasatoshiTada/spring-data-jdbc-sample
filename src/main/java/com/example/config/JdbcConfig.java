package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.core.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.SqlGeneratorSource;
import org.springframework.data.jdbc.mapping.model.DelimiterNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcMappingContext;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableJdbcRepositories(basePackages = "com.example.repository")
public class JdbcConfig {

    @Bean
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setScriptEncoding(StandardCharsets.UTF_8.name())
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataAccessStrategy dataAccessStrategy(JdbcMappingContext context) {
        return new DefaultDataAccessStrategy(new SqlGeneratorSource(context), context);
    }

    @Bean
    public NamingStrategy namingStrategy() {
        return new DelimiterNamingStrategy();
    }
}
