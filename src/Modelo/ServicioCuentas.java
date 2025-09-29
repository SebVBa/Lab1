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
    private final Map<String, Cuentas> gestor = new HashMap<>();

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

        cuenta.setActiva(true); // siempre inicia activa
        gestor.put(numeroCuenta, cuenta);
        return cuenta;
    }

    // Obtener cuenta por número
    public Cuentas obtenerCuenta(String numeroCuenta) {
        return gestor.get(numeroCuenta);
    }

    // Actualizar estado de la cuenta (activa / inactiva)
    public boolean actualizarEstado(String numeroCuenta, boolean activa) {
        Cuentas cuenta = gestor.get(numeroCuenta);
        if (cuenta == null) return false;
        cuenta.setActiva(activa);
        return true;
    }

    // Eliminar cuenta (solo si saldo = 0)
    public boolean eliminarCuenta(String numeroCuenta) throws Exception {
        Cuentas cuenta = gestor.get(numeroCuenta);
        if (cuenta == null) return false;
        if (cuenta.getSaldo() != 0) {
            throw new Exception("No se puede eliminar la cuenta, saldo distinto de 0");
        }
        gestor.remove(numeroCuenta);
        return true;
    }

    // Depósito
    public boolean depositar(String numeroCuenta, double monto) throws Exception {
        Cuentas cuenta = gestor.get(numeroCuenta);
        if (cuenta == null) throw new Exception("Cuenta no encontrada");
        if (!cuenta.isActiva()) throw new Exception("Cuenta inactiva");
        if (monto <= 0) throw new Exception("Monto inválido");

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        return true;
    }

    // Retiro
    public boolean retirar(String numeroCuenta, double monto) throws Exception {
        Cuentas cuenta = gestor.get(numeroCuenta);
        if (cuenta == null) throw new Exception("Cuenta no encontrada");
        if (!cuenta.isActiva()) throw new Exception("Cuenta inactiva");
        if (monto <= 0) throw new Exception("Monto inválido");
        if (cuenta.getSaldo() < monto) throw new Exception("Saldo insuficiente");

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        return true;
    }

    // Transferencia entre cuentas
    public boolean transferir(String origen, String destino, double monto) throws Exception {
        if (retirar(origen, monto)) {
            try {
                depositar(destino, monto);
                return true;
            } catch (Exception e) {
                // rollback
                depositar(origen, monto);
                throw new Exception("Error en la transferencia: " + e.getMessage());
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

    public Map<String, Cuentas> getGestor() {
    return gestor;
}

}