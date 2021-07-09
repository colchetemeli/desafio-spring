package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull
    @JsonProperty("product_id")
    private Integer id;
    @NotBlank
    private String productName;
    @NotBlank
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product toEntity() {
        return new Product(this.id,
                            this.productName,
                            this.type,
                            this.brand,
                            this.color,
                            this.notes);
    }
}
