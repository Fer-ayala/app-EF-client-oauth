package com.example.app_EF_client_oauth.controller;

import com.example.app_EF_client_oauth.exception.ResourceNotFoundException;
import com.example.app_EF_client_oauth.model.Producto;
import com.example.app_EF_client_oauth.service.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {

    private IProductoService iProductoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> obtenerProductos(){

        List<Producto> lista = iProductoService.listarProductos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COORDINADOR') or hasRole('ADMIN')")
    @PostMapping("/private/registrar")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto){
        return new ResponseEntity<>(iProductoService.guardarProducto(producto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/private/actualizar")
    public ResponseEntity<Producto> actualizarProducto(
            @RequestParam Integer id,
            @RequestBody Producto producto
    ){
        Producto newProducto = iProductoService.buscarProductoXId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con Id "
                        + id +" no existe"));

        newProducto.setNombre(producto.getNombre());
        newProducto.setDescripcion(producto.getDescripcion());
        newProducto.setPrecio(producto.getPrecio());
        return new ResponseEntity<>(
                iProductoService.guardarProducto(newProducto),
                HttpStatus.OK
        );
    }
}
