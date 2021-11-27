package crudpoo.clases;

public class Vehiculos {
    
    int idVehiculo;
    String nombreCompleto;
    String placa;
    int numeroRuedas;
    String color;
    String tipoVehiculo;

    public Vehiculos(int idVehiculo, String nombreCompleto, String placa, int numeroRuedas, String color, String tipoVehiculo) {
        this.idVehiculo = idVehiculo;
        this.nombreCompleto = nombreCompleto;
        this.placa = placa;
        this.numeroRuedas = numeroRuedas;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumeroRuedas() {
        return numeroRuedas;
    }

    public void setNumeroRuedas(int numeroRuedas) {
        this.numeroRuedas = numeroRuedas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    
    
}
