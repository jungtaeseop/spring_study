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
public class PayId implements Serializable {

    @EqualsAndHashCode.Include
    @Column
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Column
    private Long paySeq;

    public PayId(Long payNumber, Long paySeq){
        this.payNumber = payNumber;
        this.paySeq = paySeq;
    }


}
