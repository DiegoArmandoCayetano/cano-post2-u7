package com.universidad.apiproductos.service;

import com.universidad.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {

    private List<Producto> lista = new ArrayList<>();
    private Long contadorId = 1L;

    // Constructor con datos iniciales
    public ProductoService() {
        lista.add(new Producto(contadorId++, "Laptop", "Laptop 15 pulgadas", 1299.99));
        lista.add(new Producto(contadorId++, "Mouse", "Mouse inalámbrico", 29.99));
    }

    public List<Producto> obtenerTodos() {
        return lista;
    }

    public Producto buscarPorId(Long id) {
    return lista.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
}

    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
            lista.add(producto);
        } else {
            eliminar(producto.getId());
            lista.add(producto);
        }
        return producto;
    }

    public void eliminar(Long id) {
        lista.removeIf(p -> p.getId().equals(id));
    }
}