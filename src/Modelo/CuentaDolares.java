/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
public class CuentaDolares extends Cuentas{
    private float saldoDolares;

    public CuentaDolares(float saldoDolares, String numCuenta, Cliente cliente, boolean estado) {
        super(numCuenta, cliente, estado);
        this.saldoDolares = saldoDolares;
    }

    public CuentaDolares(String numCuenta, Cliente cliente) {
        super(numCuenta, cliente, true); 
        this.saldoDolares = 0.0f;        
    }

    public float getSaldoDolares() {
        return saldoDolares;
    }

    public void setSaldoDolares(float saldoDolares) {
        this.saldoDolares = saldoDolares;
    }
    
}
