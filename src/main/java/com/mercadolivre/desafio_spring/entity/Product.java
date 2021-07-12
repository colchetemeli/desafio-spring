package com.mercadolivre.desafio_spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDTO toDTO(){
        return new ProductDTO()
                .setId(this.product_id)
                .setProductName(this.productName)
                .setType(this.type)
                .setBrand(this.brand)
                .setColor(this.color)
                .setNotes(this.notes);
    }
}
