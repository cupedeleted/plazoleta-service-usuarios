package com.example.usuarios.domain.spi;

public interface EncriptadorClavePort {
    String encriptar(String clave);
    boolean coincide(String claveSinEncriptar, String claveEncriptada);
}
