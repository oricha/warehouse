package com.example.warehouse.controller;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.service.WarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Import(WarehouseController.class)
@RunWith(SpringRunner.class)
class WarehouseControllerTest {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WebTestClient client;

    @Test
    public void getAllProducts() {

        Mockito
                .when(this.warehouseService.getProducts())
                .thenReturn(Collections.singletonList(new ProductDto()));

        this.client
                .get()
                .uri("http://localhost:8080/warehouse/list")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

}