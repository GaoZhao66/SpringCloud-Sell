package com.imooc.order.client;

import com.imooc.order.OrderApplicationTests;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ProductClientTest extends OrderApplicationTests {


    @Autowired
    private ProductClient productClient;

    @Test
    public void decreaseStock() {


    }
}