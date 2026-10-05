package com.ecommerce.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private  Long productId;
    private  String ProductName;
    private  String Image;
    private  Integer quantity;
    private double price;
    private double discount;
    private  Double specialPrice;

}
