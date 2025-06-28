package com.franquicias.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Sucursal {
    private String nombre;
    private List<Producto> productos = new ArrayList<>();

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }
}