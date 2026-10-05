package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

@Table(name = "brands")
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    
}
