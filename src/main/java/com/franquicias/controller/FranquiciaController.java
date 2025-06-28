package com.franquicias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquicias.dto.ActualizarNombreDTO;
import com.franquicias.dto.ProductoDTO;
import com.franquicias.dto.SucursalDTO;
import com.franquicias.model.Franquicia;
import com.franquicias.model.Producto;
import com.franquicias.service.FranquiciaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @PostMapping
    public Mono<ResponseEntity<Franquicia>> agregarFranquicia(@RequestBody SucursalDTO sucursalDTO) {
        return franquiciaService.agregarFranquicia(sucursalDTO.getNombre())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/{franquiciaId}/sucursales")
    public Mono<ResponseEntity<Franquicia>> agregarSucursal(
            @PathVariable String franquiciaId,
            @RequestBody SucursalDTO sucursalDTO) {
        return franquiciaService.agregarSucursalAFranquicia(franquiciaId, sucursalDTO.getNombre())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{franquiciaId}/sucursales/{nombreSucursal}/productos")
    public Mono<ResponseEntity<Franquicia>> agregarProducto(
            @PathVariable String franquiciaId,
            @PathVariable String nombreSucursal,
            @RequestBody ProductoDTO productoDTO) {
        return franquiciaService.agregarProductoASucursal(franquiciaId, nombreSucursal, productoDTO.getNombre(), productoDTO.getStock())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{franquiciaId}/sucursales/{nombreSucursal}/productos/{nombreProducto}")
    public Mono<ResponseEntity<Franquicia>> eliminarProducto(
            @PathVariable String franquiciaId,
            @PathVariable String nombreSucursal,
            @PathVariable String nombreProducto) {
        return franquiciaService.eliminarProductoDeSucursal(franquiciaId, nombreSucursal, nombreProducto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

        @GetMapping
        public Flux<Franquicia> obtenerFranquicias() {
        return franquiciaService.obtenerTodasLasFranquicias();
        }

    @PutMapping("/{franquiciaId}/sucursales/{nombreSucursal}/productos/{nombreProducto}/stock")
    public Mono<ResponseEntity<Franquicia>> actualizarStockProducto(
            @PathVariable String franquiciaId,
            @PathVariable String nombreSucursal,
            @PathVariable String nombreProducto,
            @RequestBody ProductoDTO productoDTO) {
        return franquiciaService.actualizarStockProducto(franquiciaId, nombreSucursal, nombreProducto, productoDTO.getStock())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{franquiciaId}/productos/max-stock")
    public Flux<Producto> obtenerProductosConMaximoStock(@PathVariable String franquiciaId) {
        return franquiciaService.obtenerProductosConMaximoStock(franquiciaId);
    }

    @PutMapping("/{franquiciaId}/nombre")
    public Mono<ResponseEntity<Franquicia>> actualizarNombreFranquicia(
            @PathVariable String franquiciaId,
            @RequestBody ActualizarNombreDTO actualizarNombreDTO) {
        return franquiciaService.actualizarNombreFranquicia(franquiciaId, actualizarNombreDTO.getNuevoNombre())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{franquiciaId}/sucursales/{nombreSucursal}/nombre")
    public Mono<ResponseEntity<Franquicia>> actualizarNombreSucursal(
            @PathVariable String franquiciaId,
            @PathVariable String nombreSucursal,
            @RequestBody ActualizarNombreDTO actualizarNombreDTO) {
        return franquiciaService.actualizarNombreSucursal(franquiciaId, nombreSucursal, actualizarNombreDTO.getNuevoNombre())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{franquiciaId}/sucursales/{nombreSucursal}/productos/{nombreProducto}/nombre")
    public Mono<ResponseEntity<Franquicia>> actualizarNombreProducto(
            @PathVariable String franquiciaId,
            @PathVariable String nombreSucursal,
            @PathVariable String nombreProducto,
            @RequestBody ActualizarNombreDTO actualizarNombreDTO) {
        return franquiciaService.actualizarNombreProducto(franquiciaId, nombreSucursal, nombreProducto, actualizarNombreDTO.getNuevoNombre())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
} 