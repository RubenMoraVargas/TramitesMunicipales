package org.una.tramites.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Entity
@Table(name = "transacciones")
public @Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne()
    @JoinColumn(name = "permisos_otorgados_id")
    private PermisoOtorgado permisoOtorgado; 
     
    @Column
    private String objeto;

    @Column(length = 250)
    private String informacion; 
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date(); 
    } 
    
}
