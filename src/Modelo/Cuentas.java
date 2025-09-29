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
    private final String numeroCuenta;   // identificador único de 17 dígitos
    private final Cliente cliente;       // titular de la cuenta
    private double saldo;
    private boolean activa;              // true = activa, false = inactiva

    public Cuentas(String numeroCuenta, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.activa = true;
    }

    // Método abstracto: moneda de la cuenta
    public abstract String getMoneda(); // "Colones" o "Dólares"

    // Getters y setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getEstado() {
        return activa ? "Activa" : "Inactiva";
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", saldo=" + saldo +
                ", estado=" + getEstado() +
                ", moneda=" + getMoneda() +
                '}';
    }
}
