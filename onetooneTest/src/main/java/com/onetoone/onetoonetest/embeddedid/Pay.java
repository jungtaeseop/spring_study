package com.onetoone.onetoonetest.embeddedid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "pay_1")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pay {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private PayId id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pay")
    private List<PayShop> payShops = new ArrayList<>();

    public Pay(PayId id) {
        this.id = id;
    }
}
