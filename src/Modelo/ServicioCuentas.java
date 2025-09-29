/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author sebas
 */
public class ServicioCuentas {
    private final IGestorCuentas gestor;

    public ServicioCuentas(IGestorCuentas gestor) {
        this.gestor = Objects.requireNonNull(gestor, "Gestor requerido");
    }

    // --------------------- MÉTODOS CRUD ---------------------
    public void guardar(Cuentas cuenta) {
        gestor.guardar(cuenta);
    }

    public void actualizar(Cuentas cuenta) {
        gestor.actualizar(cuenta);
    }

    public void eliminar(String numeroCuenta) {
        gestor.eliminar(numeroCuenta);
    }

    public Cuentas buscar(String numeroCuenta) {
        return gestor.buscar(numeroCuenta);
    }

    public boolean existe(String numeroCuenta) {
        return gestor.existe(numeroCuenta);
    }

    public List<Cuentas> listar() {
        return gestor.listar();
    }

    public Cuentas ultimoRegistro() {
        return gestor.ultimoRegistro();
    }

    public IGestorCuentas getGestor() {
        return gestor;
    }

    // --------------------- GENERADOR DE NÚMERO DE CUENTA ---------------------
    public String generarNumeroCuenta() {
        Random rnd = new Random();
        String numero;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 17; i++) {
                sb.append(rnd.nextInt(10)); // dígitos 0-9
            }
            numero = sb.toString();
        } while (existe(numero)); // asegurar que sea único
        return numero;
    }
}