package com.kalayciburak.apigateway.loadbalancer.factory;

import com.kalayciburak.apigateway.loadbalancer.enums.LoadBalancerStrategy;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.stereotype.Component;

@Component
public class LoadBalancerFactory {
    /**
     * Belirtilen Load Balancer stratejisine uygun bir şekilde ReactorLoadBalancer yaratır.
     *
     * <p>Bu metot, <i>RANDOM, LEAST_CONNECTION ve ROUND_ROBIN</i> stratejilerine uygun olarak bir ReactorLoadBalancer
     * yaratır. Kullanıcılar, bu metodun çağrılarıyla belirtilen stratejiye göre load balancing işlevselliğini
     * yürütebilirler.</p>
     *
     * <ul>
     * <li><b>RANDOM</b>: Gelen istekleri rastgele seçilmiş bir servise yönlendirir.</li>
     * <li><b>ROUND_ROBIN</b>: Gelen istekleri sıralı bir şekilde servislere yönlendirir.</li>
     * </ul>
     *
     * @param strategy Kullanılacak Load Balancer stratejisi
     * @param factory  LoadBalancerClientFactory nesnesi, Load Balancer üretimi için kullanılır
     * @param name     Yaratılacak Load Balancer'ın ismi
     * @return Belirtilen stratejiye uygun bir şekilde yaratılmış ReactorLoadBalancer nesnesi
     */
    public ReactorLoadBalancer<ServiceInstance> createLoadBalancer(LoadBalancerStrategy strategy, LoadBalancerClientFactory factory, String name) {
        final var type = ServiceInstanceListSupplier.class;

        return switch (strategy) {
            case RANDOM -> new RandomLoadBalancer(factory.getLazyProvider(name, type), name);
            case ROUND_ROBIN -> new RoundRobinLoadBalancer(factory.getLazyProvider(name, type), name);
            case LEAST_CONNECTION -> throw new UnsupportedOperationException("Startegy not implemented yet");
        };
    }
}