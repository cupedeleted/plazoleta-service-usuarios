package com.example.usuarios.infrastructure.output.http;

import com.example.usuarios.domain.exception.ServicioNoDisponible;
import com.example.usuarios.domain.spi.IRestaurantPersistencePort;
import com.example.usuarios.infrastructure.config.security.TokenContext;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

public class RestaurantHttpAdapter implements IRestaurantPersistencePort {

    private final RestClient restClient;

    public RestaurantHttpAdapter(RestClient.Builder restClientBuilder, String plazaServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(plazaServiceUrl).build();
    }

    @Override
    public boolean existsByIdAndPropietarioId(Long idRestaurante, Long idPropietario) {
        try {
            Boolean response = restClient.get()
                    .uri("/api/v1/restaurant/{id}/owner/{ownerId}", idRestaurante, idPropietario)
                    .headers(h -> TokenContext.actual().ifPresent(h::setBearerAuth))
                    .retrieve()
                    .body(Boolean.class);
            return Boolean.TRUE.equals(response);
        } catch (RestClientException e) {
            throw new ServicioNoDisponible("No fue posible verificar el restaurante en el servicio de plazoleta");
        }
    }
}
