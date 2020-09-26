package com.d8tam.daedalusapi.accounts.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String firstName;

    public String lastName;

    public Boolean active;

    public String phone;

    public String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn
    public List<Agent> agents= new ArrayList<>();

    public Date created;

    public Date modified;
}