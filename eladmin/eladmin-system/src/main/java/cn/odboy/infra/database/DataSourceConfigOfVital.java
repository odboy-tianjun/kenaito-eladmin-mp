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
//import com.alibaba.druid.DbType;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.LocalCacheScope;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * 配置数据源
// *
// * @author odboy
// * @date 2025-01-20
// */
//@Slf4j
//@Configuration
//@MapperScan(
//        basePackages = {
//                "cn.odboy.modules.vital.mapper",
//        },
//        sqlSessionFactoryRef = "sqlSystemSessionFactoryVital"
//)
//public class DataSourceConfigOfVital {
//    @Value("${spring.datasource.vital.url}")
//    private String url;
//    @Value("${spring.datasource.vital.username}")
//    private String username;
//    @Value("${spring.datasource.vital.password}")
//    private String password;
//    @Value("${spring.datasource.vital.driverClassName}")
//    private String driverClassName;
//    @Value("${spring.datasource.vital.maxActive}")
//    private Integer maxActive;
//    @Value("${spring.datasource.vital.maxWait}")
//    private Long maxWait;
//    @Autowired
//    private MybatisPlusInterceptor interceptor;
//    @Autowired
//    private MyMetaObjectHandler metaObjectHandler;
//
//    @Bean
//    public DataSource dataSourceVital() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setDbType(DbType.mysql);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        // 初始连接数
//        dataSource.setInitialSize(5);
//        // 最小连接数
//        dataSource.setMinIdle(5);
//        // 最大连接数
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(maxWait);
//        // 指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除
//        dataSource.setTestWhileIdle(true);
//        // 指明是否在从池中取出连接前进行检验,如果检验失败, 则从池中去除连接并尝试取出另一个
//        dataSource.setTestOnBorrow(true);
//        // 是否在归还到池中前进行检验
//        dataSource.setTestOnReturn(false);
//        // 检测连接是否有效
//        dataSource.setValidationQuery("select 1");
//        // 配置监控统计
//        try {
//            dataSource.setFilters("stat,wall,log4j");
//            dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1000");
//        } catch (SQLException e) {
//            log.warn("配置数据源监控失败", e);
//        }
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSystemSessionFactoryVital() throws Exception {
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
//        factoryBean.setDataSource(dataSourceVital());
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
//        factoryBean.setPlugins(interceptor);
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplateVital() throws Exception {
//        return new SqlSessionTemplate(sqlSystemSessionFactoryVital());
//    }
//
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManagerVital() {
//        return new DataSourceTransactionManager(dataSourceVital());
//    }
//}