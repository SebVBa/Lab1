/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.CuentaColones;
import Modelo.CuentaDolares;
import Modelo.Cuentas;
import Modelo.ServicioCuentas;
import Vista.IVista;

/**
 *
 * @author sebas
 */


/**
 * Controlador de cuentas bancarias
 */
public class ControladorCuentas {
    private final ServicioCuentas servicio;
    private final IVista<Cuentas> vista;

    public ControladorCuentas(ServicioCuentas servicio, IVista<Cuentas> vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    // Crear y guardar una cuenta según tipo de moneda
    public void crearCuenta(String tipoMoneda, String idCliente, String estado) {
        try {
            Cliente cliente = servicio.getGestor().buscarCliente(idCliente); // tu gestor debe tener buscarCliente
            if (cliente == null) {
                vista.mostrarError("Cliente no encontrado");
                return;
            }

            Cuentas cuenta;
            if (tipoMoneda.equalsIgnoreCase("Colones")) {
                cuenta = new CuentaColones(generarNumeroCuenta(), cliente);
            } else {
                cuenta = new CuentaDolares(generarNumeroCuenta(), cliente);
            }

            cuenta.setActiva(estado.equalsIgnoreCase("Activa"));

            servicio.guardar(cuenta);
            vista.mostrarMensaje("Cuenta creada correctamente", "Éxito");
            vista.limpiar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    // Actualizar estado (activa/inactiva)
    public void actualizarEstado(String numeroCuenta, boolean activa) {
        try {
            Cuentas cuenta = servicio.buscar(numeroCuenta);
            if (cuenta == null) {
                vista.mostrarError("Cuenta no encontrada");
                return;
            }
            cuenta.setActiva(activa);
            servicio.actualizar(cuenta);
            vista.mostrarMensaje("Estado actualizado correctamente", "Éxito");
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void eliminarCuenta(String numeroCuenta) {
        try {
            if (!vista.confirmar("¿Está seguro de eliminar la cuenta?", "Eliminar")) return;
            servicio.eliminar(numeroCuenta);
            vista.mostrarMensaje("Cuenta eliminada correctamente", "Éxito");
            vista.limpiar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void buscarCuenta(String numeroCuenta) {
        try {
            Cuentas cuenta = servicio.buscar(numeroCuenta);
            if (cuenta == null) {
                vista.mostrarError("Cuenta no encontrada");
                return;
            }
            vista.mostrarDatos(cuenta);
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    // Generar un número de cuenta de ejemplo
    private String generarNumeroCuenta() {
        return "CR" + (int)(Math.random() * 1_000_000_000_000_000L);
    }
}