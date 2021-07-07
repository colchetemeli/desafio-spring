package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.entity.Product;
import com.mercadolivre.desafio_spring.util.DateHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostDTO {

    private int userId;
    @JsonProperty("id_post")
    private int id;
    @JsonDeserialize(using = DateHandler.class)
    private Date date;
    private Product detail;
    private int category;
    private double price;

    public Post toEntity() {
        return new Post()
                .setUserId(this.userId)
                .setId(this.id)
                .setDate(this.date)
                .setDetail(this.detail)
                .setCategory(this.category)
                .setPrice(this.price)
                .setHasPromo(false)
                .setDiscount(0);
    }
}
