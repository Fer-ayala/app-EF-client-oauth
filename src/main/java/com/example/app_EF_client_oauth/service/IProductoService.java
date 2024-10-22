package com.example.app_EF_client_oauth.service;

import com.example.app_EF_client_oauth.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarProductos();
    Optional<Producto> buscarProductoXId(Integer id);
    Producto guardarProducto(Producto p);
}
