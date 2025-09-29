/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
public class CuentaDolares extends Cuentas {
    public CuentaDolares(String numeroCuenta, Cliente cliente) {
        super(numeroCuenta, cliente);
    }

    @Override
    public String getMoneda() {
        return "DOLARES";
    }
}
