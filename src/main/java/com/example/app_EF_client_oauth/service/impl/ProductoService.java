package com.example.app_EF_client_oauth.service.impl;

import com.example.app_EF_client_oauth.model.Producto;
import com.example.app_EF_client_oauth.repository.ProductoRepository;
import com.example.app_EF_client_oauth.service.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoService implements IProductoService {

    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarProductoXId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardarProducto(Producto p) {
        return productoRepository.save(p);
    }

}
