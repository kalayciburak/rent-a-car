package com.kalayciburak.apigateway.loadbalancer.config;

import com.kalayciburak.apigateway.loadbalancer.enums.LoadBalancerStrategy;
import com.kalayciburak.apigateway.loadbalancer.factory.LoadBalancerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class LoadBalancerConfig {
    private final LoadBalancerFactory loadBalancerFactory;

    public LoadBalancerConfig(LoadBalancerFactory loadBalancerFactory) {this.loadBalancerFactory = loadBalancerFactory;}

    /**
     * Bu metot, belirtilen yük dengesini (Load Balancer) stratejisine göre bir ReactorLoadBalancer yaratır ve Spring Cloud
     * Gateway'e yükler.
     *
     * <p>Bu örnekte, load balancer stratejisi çevresel değişkenlerden alınır ve stratejiye uygun bir ReactorLoadBalancer
     * yaratılır. Yaratılan Load Balancer, belirli bir istek stratejisine uygun olarak çalışır.</p>
     *
     * @param env     Çevresel değişkenleri sağlayan Environment nesnesi
     * @param factory LoadBalancerClientFactory nesnesi, Load Balancer üretimi için kullanılır
     * @return Belirtilen stratejiye uygun bir şekilde yaratılmış ReactorLoadBalancer nesnesi
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment env, LoadBalancerClientFactory factory) {
        var name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        var strategyProperty = env.getProperty("loadbalancer.strategy", LoadBalancerStrategy.ROUND_ROBIN.name());
        var strategy = LoadBalancerStrategy.fromString(strategyProperty);

        return loadBalancerFactory.createLoadBalancer(strategy, factory, name);
    }
}