package com.example.warehouse.controller;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    /**
     * Get all products and quantity of each that is an available with the current inventory
     * GET /warehouse/list
     * @return list of Products
     */
    @RequestMapping(value = "/list")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = warehouseService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Deleted a Product and update the inventory accordingly
     * end point will remove Product from the system if found.
     * DEL /warehouse/deleteproduct/$article_id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        warehouseService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
