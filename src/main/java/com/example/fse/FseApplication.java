package com.example.fse;

import javax.sql.DataSource;
import java.sql.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
/**
 * Spring Main class
 *
 * @author live2skull
 * @since 1.0
 */
public class FseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FseApplication.class, args);
    }
}
