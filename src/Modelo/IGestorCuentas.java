/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
import java.util.List;

public interface IGestorCuentas {
    void guardar(Cuentas cuenta);
    void actualizar(Cuentas cuenta);
    void eliminar(String numeroCuenta);
    Cuentas buscar(String numeroCuenta);
    boolean existe(String numeroCuenta);
    List<Cuentas> listar();
    Cuentas ultimoRegistro();

    public Cliente buscarCliente(String idCliente);
}
