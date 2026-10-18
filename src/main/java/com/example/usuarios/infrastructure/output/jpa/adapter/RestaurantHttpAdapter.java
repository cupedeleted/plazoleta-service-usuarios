package com.example.usuarios.infrastructure.output.jpa.adapter;

import com.example.usuarios.domain.spi.IRestaurantPersistencePort;
import org.springframework.web.client.RestClient;

public class RestaurantHttpAdapter implements IRestaurantPersistencePort {

    private final RestClient restClient;

    public RestaurantHttpAdapter(RestClient.Builder restClientBuilder, String plazaServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(plazaServiceUrl).build();
    }

    @Override
    public boolean existsByIdAndPropietarioId(Long idRestaurante, Long idPropietario) {
        try {
            Boolean response = restClient.get()
                    .uri("/api/v1/restaurants/{id}/owner/{ownerId}", idRestaurante, idPropietario)
                    .retrieve()
                    .body(Boolean.class);
            return Boolean.TRUE.equals(response);
        } catch (Exception e) {
            return false;
        }
    }
}
