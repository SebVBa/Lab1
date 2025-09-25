/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ServicioClientes;
import Modelo.ServicioCuentas;
import Vista.IVista;

/**
 *
 * @author sebas
 */
public class ControladorCuentas {
    private final ServicioCuentas servicioCu;
    private final ServicioClientes servicioCli;
    private final IVista vista;

    public ControladorCuentas(ServicioCuentas servicioCu, ServicioClientes servicioCli, IVista vista) {
        this.servicioCu = servicioCu;
        this.servicioCli = servicioCli;
        this.vista = vista;
    }

    public void crear(String id){
        try {
            
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
    public void actualizar(){
        try {
            
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
    public void eliminar(String numCuenta){
        try {
            
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void buscar(String id){
        try {
            
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
}
