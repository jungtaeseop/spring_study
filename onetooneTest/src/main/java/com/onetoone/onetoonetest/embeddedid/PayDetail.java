package com.onetoone.onetoonetest.embeddedid;

import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pay_detail_1")
@NoArgsConstructor
public class PayDetail {

    @EmbeddedId
    private PayDetailId id;
}
