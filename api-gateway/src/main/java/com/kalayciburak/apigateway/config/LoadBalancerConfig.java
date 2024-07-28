package com.kalayciburak.apigateway.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class LoadBalancerConfig {
    /**
     * Bu metot, {@link Environment} nesnesinden alınan servis adıyla
     * bir {@link RandomLoadBalancer} oluşturur. Servis adı, route tanımında
     * kullanılan <code>lb://example-service</code> URI'si ile belirlenir.
     * Ardından {@link RandomLoadBalancer}, belirtilen servis adı için bir
     * LoadBalancer oluşturur.
     *
     * @param env     Ortam değişkenlerini sağlayan {@link Environment} nesnesi
     * @param factory {@link LoadBalancerClientFactory} nesnesi, LoadBalancer oluşturmak için kullanılır
     * @return Belirtilen servis adı için bir {@link RandomLoadBalancer}
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment env, LoadBalancerClientFactory factory) {
        var name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}