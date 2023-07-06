package com.onetoone.onetoonetest.embeddedid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
@NoArgsConstructor
public class PayDetailId implements Serializable {

    @EqualsAndHashCode.Include
    private PayId payId;

    @EqualsAndHashCode.Include
    @Column
    private  Long payDetailId;

    public PayDetailId(PayId payId, Long payDetailId){
        this.payId = payId;
        this.payDetailId = payDetailId;
    }
}
