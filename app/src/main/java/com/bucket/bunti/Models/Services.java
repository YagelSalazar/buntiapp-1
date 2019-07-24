package com.bucket.bunti.Models;

public class Services {
    private String address;
    private String longitud;
    private String latitud;
    private String hora;
    private String fecha;
    private String usuario;
    private String metodo_pago;

    public Services() {

    }

    public Services(String address, String longitud, String latitud, String hora, String fecha, String usuario, String metodo_pago) {
        this.address = address;
        this.longitud = longitud;
        this.latitud = latitud;
        this.hora = hora;
        this.fecha = fecha;
        this.usuario = usuario;
        this.metodo_pago = metodo_pago;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
}
