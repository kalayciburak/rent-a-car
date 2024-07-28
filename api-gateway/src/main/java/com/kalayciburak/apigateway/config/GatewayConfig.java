package com.kalayciburak.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {
    /**
     * Bu metot, custom route tanımları oluşturur ve Spring Cloud Gateway'e yükler.
     *
     * <p>Bu örnekte, "/inventory-service/api/**" pattern'ine uyan istekler,
     * "inventory-service" servisine yönlendirilir. Yönlendirme sırasında çeşitli
     * filtreler uygulanır ve circuit breaker ile hata durumlarında fallback işlemi yapılır.
     *
     * <ul>
     * <li><b>RemoveRequestHeader</b>: "Cookie" başlığını kaldırır.</li>
     * <li><b>StripPrefix</b>: URL'nin ilk kısmını kaldırır.</li>
     * <li><b>Retry</b>: 1 kere yeniden deneme yapar ve 503 (Service Unavailable) durum kodunda geçerlidir.</li>
     * <li><b>CircuitBreaker</b>: "inventoryService" isimli circuit breaker ile fallback URI'sine yönlendirir.</li>
     * </ul>
     *
     * @param builder RouteLocatorBuilder, route tanımlarını inşa etmek için kullanılır.
     * @return Oluşturulan RouteLocator nesnesi.
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inventory-service", r -> r.path("/inventory-service/api/**").and()
                        .method("GET", "POST", "PUT", "DELETE")
                        .filters(f -> f.removeRequestHeader("Cookie")
                                .stripPrefix(1)
                                .retry(config -> config.setRetries(1).setStatuses(HttpStatus.SERVICE_UNAVAILABLE))
                                .circuitBreaker(config -> config.setName("inventoryService") // ? yml'de belirtilen circuit breaker ismi
                                        .setFallbackUri("forward:/fallback")
                                        .setRouteId("inventory-service")))
                        .uri("lb://inventory-service"))
                .build();
    }
}