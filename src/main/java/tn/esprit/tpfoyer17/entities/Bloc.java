package tn.esprit.tpfoyer17.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idBloc;

    String nomBloc;

    long capaciteBloc;

    @ToString.Exclude
    @ManyToOne
    Foyer foyer;

    @ToString.Exclude
    @OneToMany(mappedBy = "bloc")//Bloc 1--* Chambre
    Set<Chambre> chambres;
    /*
    bloc(1,G,
    {
        "idBloc":1,
        "nomBloc": "G",
        "chambres":[
        {
            "idChambre":1
            "bloc":{
               "idBloc":1,
                "nomBloc": "G",
                    "chambres":[
                        {
                    "idChambre":1
                    "bloc":{
                       "idBloc":1,
                    "nomBloc": "G",
                        }
                    }
                ]
            }
        }
        ]

    }
    DTO: data transfert object
     */
}

