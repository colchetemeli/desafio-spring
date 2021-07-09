package com.mercadolivre.desafio_spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDTO toDTO(){
        return new ProductDTO(this.product_id,
                                this.productName,
                                this.type,
                                this.brand,
                                this.color,
                                this.notes);
    }
}
