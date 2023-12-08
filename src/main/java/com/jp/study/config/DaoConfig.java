package com.jp.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("com.jp.study.dao")
public class DaoConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        
        // MyBatisの設定ファイルの場所を指定
        factory.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));
        
        // Mapperの場所を指定
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/jp/study/dao/*.xml"));
        
        return factory.getObject();
    }
}
