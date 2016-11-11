package ru.click.core.config;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.click.core.repository.EntityRepository;
import ru.click.core.repository.QueryDslRepository;
import ru.click.core.repository.QueryDslRepositoryImpl;
import ru.click.core.repository.QueryFactory;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ConditionalOnClass({EntityRepository.class, QueryDslRepository.class, QueryFactory.class})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ComponentScan("ru.click.core.repository")
public class JpaSubSystemAutoConfiguration {


    /**
     * Контейнер бинов
     */
    @Autowired
    private ListableBeanFactory ctx;

    /**
     * Корзинка, в которой хранятся ссылки на Созданные репозитории Spring Data JPA
     * с доступом по типу модели
     *
     * @return корзинка репозиториев
     */
    @Bean(destroyMethod = "clear")
    public Map<Class, QueryDslRepository> repositoryBucket() {
        final Collection<QueryDslRepository> repositories = ctx.getBeansOfType(QueryDslRepository.class).values();
        Map<Class, QueryDslRepository> map = new ConcurrentHashMap<>(repositories.size());
        for (QueryDslRepository repository : repositories) {
            map.put(repository.getJavaType(), repository);
        }
        return map;
    }

    @Configuration
    @EnableTransactionManagement
    @ImportAutoConfiguration(DataSourceAutoConfiguration.class)
    @EnableJpaRepositories(basePackages = "ru.click.core.repository.domain",
            repositoryBaseClass = QueryDslRepositoryImpl.class)
    protected static class JpaConfig {

        @Autowired
        private DataSource dataSource;

        @Bean
        public EntityManagerFactory entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource);
            entityManagerFactoryBean.setPackagesToScan("ru.znamenka.jpa.model");
            entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactoryBean.afterPropertiesSet();
            return entityManagerFactoryBean.getObject();
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            return new JpaTransactionManager(entityManagerFactory());

        }

    }
}
