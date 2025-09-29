/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Cuentas;
import Modelo.ServicioClientes;
import Modelo.ServicioCuentas;
import Vista.IVista;

/**
 *
 * @author sebas
 */
public class ControladorCuentas {
    private final ServicioCuentas servicioCuentas;
    private final ServicioClientes servicioClientes;
    private final IVista<Cuentas> vista;

    public ControladorCuentas(ServicioCuentas servicioCuentas, ServicioClientes servicioClientes, IVista<Cuentas> vista) {
        this.servicioCuentas = servicioCuentas;
        this.servicioClientes = servicioClientes;
        this.vista = vista;
    }

    // Crear cuenta
    public void crearCuenta(String tipo, String idCliente) {
        try {
            Cliente titular = servicioClientes.buscar(idCliente);
            Cuentas cuenta = servicioCuentas.crearCuenta(tipo, titular);
            vista.mostrarMensaje("Cuenta", "Cuenta creada con éxito. Número: " + cuenta.getNumeroCuenta() + ", Titular: " + titular.getNombre());
        } catch (Exception e) {
            vista.mostrarError("Error al crear cuenta: " + e.getMessage());
        }
    }

    // Actualizar estado de cuenta (activa/inactiva)
    public void actualizarEstado(String numeroCuenta, boolean activa) {
        try {
            Cuentas cuenta = servicioCuentas.getGestor().get(numeroCuenta);
            if (cuenta == null) {
                vista.mostrarError("Cuenta no encontrada");
                return;
            }
            cuenta.setActiva(activa);
            vista.mostrarMensaje("Cuenta", "Estado de la cuenta actualizado: " + (activa ? "Activa" : "Inactiva"));
        } catch (Exception e) {
            vista.mostrarError("Error al actualizar estado: " + e.getMessage());
        }
    }

    // Eliminar cuenta (solo saldo = 0)
    public void eliminarCuenta(String numeroCuenta) {
        try {
            servicioCuentas.eliminarCuenta(numeroCuenta);
            vista.mostrarMensaje("Cuenta", "Cuenta eliminada correctamente");
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    // Buscar cuenta
    public void buscarCuenta(String numeroCuenta) {
        try {
            Cuentas cuenta = servicioCuentas.getGestor().get(numeroCuenta);
            if (cuenta != null) {
                vista.mostrarMensaje("Cuenta", "Cuenta: " + cuenta.getNumeroCuenta() +
                        ", Titular: " + cuenta.getCliente().getNombre() +
                        ", Saldo: " + cuenta.getSaldo() +
                        ", Estado: " + (cuenta.isActiva() ? "Activa" : "Inactiva") +
                        ", Moneda: " + cuenta.getMoneda());
            } else {
                vista.mostrarError("Cuenta no encontrada");
            }
        } catch (Exception e) {
            vista.mostrarError("Error al buscar cuenta: " + e.getMessage());
        }
    }

    // Depósito
    public void depositar(String numeroCuenta, double monto) {
        try {
            servicioCuentas.depositar(numeroCuenta, monto);
            Cuentas cuenta = servicioCuentas.getGestor().get(numeroCuenta);
            vista.mostrarMensaje("Depósito", "Depósito realizado con éxito. Saldo actual: " + cuenta.getSaldo());
        } catch (Exception e) {
            vista.mostrarError("Error al depositar: " + e.getMessage());
        }
    }

    // Retiro
    public void retirar(String numeroCuenta, double monto) {
        try {
            servicioCuentas.retirar(numeroCuenta, monto);
            Cuentas cuenta = servicioCuentas.getGestor().get(numeroCuenta);
            vista.mostrarMensaje("Retiro", "Retiro realizado con éxito. Saldo actual: " + cuenta.getSaldo());
        } catch (Exception e) {
            vista.mostrarError("Error al retirar: " + e.getMessage());
        }
    }

    // Transferencia
    public void transferir(String origen, String destino, double monto) {
        try {
            servicioCuentas.transferir(origen, destino, monto);
            Cuentas cuentaOrigen = servicioCuentas.getGestor().get(origen);
            Cuentas cuentaDestino = servicioCuentas.getGestor().get(destino);
            vista.mostrarMensaje("Transferencia", "Transferencia realizada con éxito. Saldo origen: " + cuentaOrigen.getSaldo() +
                    ", Saldo destino: " + cuentaDestino.getSaldo());
        } catch (Exception e) {
            vista.mostrarError("Error al transferir: " + e.getMessage());
        }
    }
}
