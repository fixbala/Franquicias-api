package com.franquicias.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Producto {
    private String nombre;
    private Integer stock;

    public Producto(String nombre, Integer stock) {
        this.nombre = nombre;
        this.stock = stock;
    }
} 