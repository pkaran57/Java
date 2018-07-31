package com.ker.springboot.zip;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "ZIP_DATA")
@NamedQuery(name="getAllZips", query = "select z from Zip z")
public class Zip {

    //@GeneratedValue     // use this only if JPA to generate ID
    @Id
    private int zip;

    @Column(name = "LOCATION_INFO")
    private String locationInfo;
}
