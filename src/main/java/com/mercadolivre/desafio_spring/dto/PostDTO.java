package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.entity.Product;
import com.mercadolivre.desafio_spring.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int userId;
    @JsonProperty("id_post")
    private int id;
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone =  Constants.DEFAULT_TIMEZONE)
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
