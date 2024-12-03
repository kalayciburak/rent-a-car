package com.kalayciburak.apigateway.loadbalancer.enums;

public enum LoadBalancerStrategy {
    RANDOM,
    ROUND_ROBIN,
    LEAST_CONNECTION;

    /**
     * Belirtilen string değerini uygun Load Balancer stratejisine dönüştürür.
     *
     * <p>Bu metot, verilen string değerine göre uygun Load Balancer stratejisini belirler.
     * <i>"random", "round_robin", "round-robin"</i> veya <i>"least_connection"</i> gibi değerler
     * kabul edilir ve bu değerlere uygun strateji dönülür.</p>
     *
     * @param value Yük dengesini sağlamak için kullanılacak stratejinin adı
     * @return Belirtilen değere uygun Load Balancer stratejisi
     * @throws IllegalArgumentException Bilinmeyen bir strateji belirtildiğinde fırlatılır
     */
    public static LoadBalancerStrategy fromString(String value) {
        return switch (value.toLowerCase().replace("-", "_")) {
            case "random" -> RANDOM;
            case "least_connection", "least-connection" -> LEAST_CONNECTION;
            default -> ROUND_ROBIN;
        };
    }
}