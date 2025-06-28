package com.franquicias.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquicias.model.Franquicia;
import com.franquicias.model.Producto;
import com.franquicias.model.Sucursal;
import com.franquicias.repository.FranquiciaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Mono<Franquicia> agregarFranquicia(String nombre) {
        return franquiciaRepository.save(new Franquicia(nombre));
    }

    public Mono<Franquicia> agregarSucursalAFranquicia(String franquiciaId, String nombreSucursal) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.agregarSucursal(new Sucursal(nombreSucursal));
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Mono<Franquicia> agregarProductoASucursal(String franquiciaId, String nombreSucursal, String nombreProducto, Integer stock) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.getSucursales().stream()
                            .filter(s -> s.getNombre().equals(nombreSucursal))
                            .findFirst()
                            .ifPresent(sucursal -> sucursal.agregarProducto(new Producto(nombreProducto, stock)));
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Flux<Franquicia> obtenerTodasLasFranquicias() {
    return franquiciaRepository.findAll();
}
    public Mono<Franquicia> eliminarProductoDeSucursal(String franquiciaId, String nombreSucursal, String nombreProducto) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.getSucursales().stream()
                            .filter(s -> s.getNombre().equals(nombreSucursal))
                            .findFirst()
                            .ifPresent(sucursal -> sucursal.getProductos().removeIf(p -> p.getNombre().equals(nombreProducto)));
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Mono<Franquicia> actualizarStockProducto(String franquiciaId, String nombreSucursal, String nombreProducto, Integer nuevoStock) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.getSucursales().stream()
                            .filter(s -> s.getNombre().equals(nombreSucursal))
                            .findFirst()
                            .ifPresent(sucursal -> sucursal.getProductos().stream()
                                    .filter(p -> p.getNombre().equals(nombreProducto))
                                    .findFirst()
                                    .ifPresent(producto -> producto.setStock(nuevoStock)));
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Flux<Producto> obtenerProductosConMaximoStock(String franquiciaId) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMapMany(franquicia -> {
                    List<Sucursal> sucursales = franquicia.getSucursales();
                    return Flux.fromIterable(sucursales)
                            .flatMap(sucursal -> {
                                Optional<Producto> maxProduct = sucursal.getProductos().stream()
                                        .max(Comparator.comparing(Producto::getStock));
                                return maxProduct.map(producto -> {
                                    producto.setNombre(sucursal.getNombre() + " - " + producto.getNombre());
                                    return Mono.just(producto);
                                }).orElse(Mono.empty());
                            });
                });
    }

    public Mono<Franquicia> actualizarNombreFranquicia(String franquiciaId, String nuevoNombre) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.setNombre(nuevoNombre);
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Mono<Franquicia> actualizarNombreSucursal(String franquiciaId, String nombreSucursal, String nuevoNombre) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.getSucursales().stream()
                            .filter(s -> s.getNombre().equals(nombreSucursal))
                            .findFirst()
                            .ifPresent(sucursal -> sucursal.setNombre(nuevoNombre));
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Mono<Franquicia> actualizarNombreProducto(String franquiciaId, String nombreSucursal, String nombreProducto, String nuevoNombre) {
        return franquiciaRepository.findById(franquiciaId)
                .flatMap(franquicia -> {
                    franquicia.getSucursales().stream()
                            .filter(s -> s.getNombre().equals(nombreSucursal))
                            .findFirst()
                            .ifPresent(sucursal -> sucursal.getProductos().stream()
                                    .filter(p -> p.getNombre().equals(nombreProducto))
                                    .findFirst()
                                    .ifPresent(producto -> producto.setNombre(nuevoNombre)));
                    return franquiciaRepository.save(franquicia);
                });
    }
}