/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author ilope
 */
public class GestorCuentasMem implements IGestorCuentas {
    private final Map<String, Cuentas> cuentas;
    private final List<Cuentas> cuentasEliminadas;
    private Cuentas ultima;

    public GestorCuentasMem() {
        this.cuentas = new HashMap<>();
        this.cuentasEliminadas = new ArrayList<>();
        this.ultima = null;
    }

    @Override
    public void guardar(Cuentas cuenta) {
        Objects.requireNonNull(cuenta, "Cuenta requerida");
        if (cuentas.putIfAbsent(cuenta.getNumeroCuenta(), cuenta) != null) {
            throw new IllegalStateException("Ya existe una cuenta con número=" + cuenta.getNumeroCuenta());
        }
        this.ultima = cuenta;
    }

    @Override
    public void actualizar(Cuentas cuenta) {
        Objects.requireNonNull(cuenta, "Cuenta requerida");
        String numero = cuenta.getNumeroCuenta();
        if (!cuentas.containsKey(numero)) {
            throw new IllegalArgumentException("No existe cuenta con número=" + numero);
        }
        cuentas.put(numero, cuenta);
        this.ultima = cuenta;
    }

    @Override
    public void eliminar(String numeroCuenta) {
        Objects.requireNonNull(numeroCuenta, "Número de cuenta requerido");
        Cuentas eliminada = cuentas.remove(numeroCuenta);
        if (eliminada == null) {
            throw new IllegalArgumentException("No existe cuenta con número=" + numeroCuenta);
        }
        cuentasEliminadas.add(eliminada);
        if (ultima != null && ultima.getNumeroCuenta().equals(numeroCuenta)) {
            this.ultima = null;
        }
    }

    @Override
    public Cuentas buscar(String numeroCuenta) {
        Objects.requireNonNull(numeroCuenta, "Número de cuenta requerido");
        ultima = cuentas.get(numeroCuenta);
        return ultima;
    }

    @Override
    public boolean existe(String numeroCuenta) {
        Objects.requireNonNull(numeroCuenta, "Número de cuenta requerido");
        return cuentas.containsKey(numeroCuenta);
    }

    @Override
    public List<Cuentas> listar() {
        return new ArrayList<>(cuentas.values());
    }

    @Override
    public Cuentas ultimoRegistro() {
        return ultima;
    }

    public List<Cuentas> listarEliminadas() {
        return Collections.unmodifiableList(cuentasEliminadas);
    }
}