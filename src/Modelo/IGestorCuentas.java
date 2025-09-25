/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
public interface IGestorCuentas {
    void guardar(Cuentas cuenta);
    void actualizar(Cuentas cuenta);
    void eliminar(String numCuenta);
    Cuentas buscar(String numCuenta);
}
