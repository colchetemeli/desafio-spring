package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.entity.Product;
import com.mercadolivre.desafio_spring.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonPropertyOrder({ "id_post", "date", "detail", "category", "price"})
public class PostDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
                .setPrice(this.price);
    }
}
