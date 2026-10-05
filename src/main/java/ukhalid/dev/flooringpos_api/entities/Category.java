package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    
}
