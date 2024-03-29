package getpizza.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Ingrediente {

    private String nombre;
    
    private float precio;
    
    public Ingrediente(String nombre, float precio) {
    	this.nombre = nombre;
    	this.setPrecio(precio);
    }

    @objid ("3f3fdac8-1a21-421a-a108-3a8f6354f267")
    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    @objid ("37f3ac80-03d9-43e6-8da1-cf706fe54280")
    public void setNombre(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
