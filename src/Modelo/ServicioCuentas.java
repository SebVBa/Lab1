/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sebas
 */
public class ServicioCuentas {
         private static final String CODIGO_BANCO = "123"; // prefijo fijo de 3 dígitos
    private static final String ARCHIVO_CONSECUTIVO = "consecutivo.txt";
    private static long consecutivo = 1;

    // Simulación de base de datos en memoria
    private Map<String, Cuentas> cuentas = new HashMap<>();

    // Constructor: carga consecutivo desde archivo
    public ServicioCuentas() {
        cargarConsecutivo();
    }

    // Generar número de cuenta único
    private String generarNumeroCuenta() {
        String numConsecutivo = String.format("%014d", consecutivo++);
        guardarConsecutivo();
        return CODIGO_BANCO + numConsecutivo;
    }

    // Crear una nueva cuenta
    public Cuentas crearCuenta(String tipo, Cliente cliente) {
        String numeroCuenta = generarNumeroCuenta();
        Cuentas cuenta;

        if ("COLONES".equalsIgnoreCase(tipo)) {
            cuenta = new CuentaColones(numeroCuenta, cliente);
        } else {
            cuenta = new CuentaDolares(numeroCuenta, cliente);
        }

        cuentas.put(numeroCuenta, cuenta);
        return cuenta;
    }

    // Obtener cuenta por número
    public Cuentas obtenerCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    // Depósito
    public boolean depositar(String numeroCuenta, double monto) {
        Cuentas cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null || monto <= 0) return false;
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        return true;
    }

    // Retiro
    public boolean retirar(String numeroCuenta, double monto) {
        Cuentas cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null || monto <= 0 || cuenta.getSaldo() < monto) return false;
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        return true;
    }

    // Transferencia entre cuentas
    public boolean transferir(String origen, String destino, double monto) {
        if (retirar(origen, monto)) {
            if (depositar(destino, monto)) {
                return true;
            } else {
                // rollback del retiro si falla depósito
                depositar(origen, monto);
            }
        }
        return false;
    }

    // ===============================
    // Métodos privados de persistencia
    // ===============================

    private void cargarConsecutivo() {
        File file = new File(ARCHIVO_CONSECUTIVO);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                if (line != null) {
                    consecutivo = Long.parseLong(line.trim());
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error al leer consecutivo, reiniciando en 1");
                consecutivo = 1;
            }
        }
    }

    private void guardarConsecutivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CONSECUTIVO))) {
            bw.write(Long.toString(consecutivo));
        } catch (IOException e) {
            System.err.println("Error al guardar consecutivo: " + e.getMessage());
        }
    }
}
