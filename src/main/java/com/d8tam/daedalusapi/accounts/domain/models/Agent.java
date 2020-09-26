package com.d8tam.daedalusapi.accounts.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Agent {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public String location;

    public String status;

    public Long accountId;

    public Date created;

    public Date modified;
}
