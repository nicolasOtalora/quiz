package edu.co.sergio.mundo.vo;

public class ObraDeArteVO {
    
    
    private String nombre;
    private String descripcion;
    private String estilo;
    private double valor; 
    private ArtistaVO artista;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ArtistaVO getArtista() {
        return artista;
    }

    public void setArtista(ArtistaVO artista) {
        this.artista = artista;
    }

    
    
    
}
