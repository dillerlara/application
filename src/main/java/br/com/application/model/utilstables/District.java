package br.com.application.model.utilstables;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.locationtech.jts.geom.Geometry;

@Entity
public class District {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false, unique = true)
	private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private Boolean active;

    @Column()
    private BigDecimal latitude;

    @Column
    private BigDecimal Longitude;
    
}
