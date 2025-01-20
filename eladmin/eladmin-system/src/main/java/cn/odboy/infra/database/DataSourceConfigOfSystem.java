///*
// *  Copyright 2022-2025 Tian Jun
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *  http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//package cn.odboy.infra.database;
//
//import cn.odboy.infra.mybatisplus.MyMetaObjectHandler;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.LocalCacheScope;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 配置数据源
// *
// * @author odboy
// * @date 2025-01-15
// */
//@Slf4j
//@Configuration
//@MapperScan(
//        basePackages = {
//                "cn.odboy.mapper",
//                "cn.odboy.modules.maint.mapper",
//                "cn.odboy.modules.quartz.mapper",
//                "cn.odboy.modules.system.mapper",
//        },
//        sqlSessionFactoryRef = "sqlSystemSessionFactorySystem"
//)
//public class DataSourceConfigOfSystem {
//    @Value("${spring.datasource.system.url}")
//    private String url;
//    @Value("${spring.datasource.system.username}")
//    private String username;
//    @Value("${spring.datasource.system.password}")
//    private String password;
//    @Value("${spring.datasource.system.driverClassName}")
//    private String driverClasName;
//    @Autowired
//    private MybatisPlusInterceptor interceptor;
//    @Autowired
//    private MyMetaObjectHandler metaObjectHandler;
//
//    @Bean
//    public DataSource dataSourceSystem() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(driverClasName);
//        dataSource.setJdbcUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSystemSessionFactorySystem() throws Exception {
//        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        // 开启 Mybatis 二级缓存，默认为 true
//        configuration.setCacheEnabled(false);
//        // 设置本地缓存作用域, Mybatis 一级缓存, 默认为 SESSION
//        // 同一个 session 相同查询语句不会再次查询数据库
//        // 微服务中, 建议设置为STATEMENT, 即关闭一级缓存
//        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
//        // 是否开启自动驼峰命名规则（camel case）映射, 即从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射
//        configuration.setMapUnderscoreToCamelCase(true);
//        factoryBean.setConfiguration(configuration);
//        // 配置数据源
//        factoryBean.setDataSource(dataSourceSystem());
//        // MyBatis Mapper 所对应的 XML 文件位置
//        // Maven 多模块项目的扫描路径需以 classpath*: 开头 （即加载多个 jar 包下的 XML 文件）
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*.xml"));
//        // MyBatis-Plus 全局策略中的 DB 策略配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        // 是否控制台 print mybatis-plus 的 LOGO
//        globalConfig.setBanner(false);
//        globalConfig.setMetaObjectHandler(metaObjectHandler);
//        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//        // 全局默认主键类型, 这里为自增主键
//        dbConfig.setIdType(IdType.AUTO);
//        dbConfig.setLogicDeleteField("available");
//        // 逻辑未删除值(逻辑删除下有效)
//        dbConfig.setLogicNotDeleteValue("1");
//        // 逻辑已删除值(逻辑删除下有效)
//        dbConfig.setLogicDeleteValue("0");
//        // 表名是否使用驼峰转下划线命名,只对表名生效
//        dbConfig.setTableUnderline(true);
//        globalConfig.setDbConfig(dbConfig);
//        factoryBean.setGlobalConfig(globalConfig);
//        // 配置插件
//        factoryBean.addPlugins(interceptor);
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplateSystem() throws Exception {
//        return new SqlSessionTemplate(sqlSystemSessionFactorySystem());
//    }
//
//    @Bean
//    @Primary
//    public DataSourceTransactionManager dataSourceTransactionManagerSystem() {
//        return new DataSourceTransactionManager(dataSourceSystem());
//    }
//}
