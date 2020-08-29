package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "permisos_otorgados")
public @Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class PermisoOtorgado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column
    private boolean estado;

    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuario; 
    
    @ManyToOne 
    @JoinColumn(name="permisos_id")
    private Permiso permiso; 
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro; 
 
     @OneToMany(mappedBy = "permisoOtorgado", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Transaccion> transacciones= new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date(); 
    } 
}
