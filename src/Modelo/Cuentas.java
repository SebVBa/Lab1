/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
public abstract class Cuentas {
    private final String numCuenta;
    private final Cliente cliente;
    private boolean estado;

    public Cuentas(String numCuenta, Cliente cliente, boolean estado) {
        this.numCuenta = numCuenta;
        this.cliente = cliente;
        this.estado = estado;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    double getSaldo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setSaldo(double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
