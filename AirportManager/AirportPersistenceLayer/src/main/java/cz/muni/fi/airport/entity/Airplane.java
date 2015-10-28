package cz.muni.fi.airport.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jakub Stromský
 */
@Entity
public class Airplane {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(unique = true, nullable = false)
    private String name;
    
    @NotNull
    private int capacity;
    
    @NotNull
    private String type;

    public Airplane(Long id) {
        this.id = id;
    }

    public Airplane() {
    }

    public String getName() {
        return name;
    }   
    
    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.getName());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Airplane)) {
            return false;
        }
        final Airplane other = (Airplane) obj;
        return Objects.equals(this.getName(), other.getName());       
    }
      
}
