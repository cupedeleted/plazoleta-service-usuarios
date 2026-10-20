package com.example.usuarios.domain.spi;

public interface IRestaurantPersistencePort {
    boolean existsByIdAndPropietarioId(Long idRestaurante, Long idPropietario);
}