package com.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

// Defining the web service interface
@WebService
public interface InventoryService {

    // Defining the inventory check method
    @WebMethod(operationName = "checkInventory")
    public String checkInventory(
            @WebParam(name = "productId") String productId,
            @WebParam(name = "productQuantity") int productQuantity);

}
