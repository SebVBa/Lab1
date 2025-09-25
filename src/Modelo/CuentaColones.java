/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebas
 */
public class CuentaColones extends Cuentas{
    
    private float saldoColones;

    public CuentaColones(float saldoColones, String numCuenta, Cliente cliente, boolean estado) {
        super(numCuenta, cliente, estado);
        this.saldoColones = saldoColones;
    }

    public CuentaColones(String numCuenta, Cliente cliente) {
        super(numCuenta, cliente, true); 
        this.saldoColones = 0.0f;        
    }

    public float getSaldoColones() {
        return saldoColones;
    }

    public void setSaldoColones(float saldoColones) {
        this.saldoColones = saldoColones;
    }

}
