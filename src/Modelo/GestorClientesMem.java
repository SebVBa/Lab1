/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jprod
 */
import java.util.*;

public class GestorClientesMem implements IGestorClientes {
    private final Map<String, Cliente> map;
    private final List<Cliente> mapDeleted;
    private Cliente cliente;

    public GestorClientesMem() {
        this.map = new HashMap<>();
        this.mapDeleted = new ArrayList<>();
        this.cliente = null;
    }

    @Override
    public Cliente ultimoRegistro() {
        return cliente;
    }

    @Override
    public void guardar(Cliente cliente) {
        Objects.requireNonNull(cliente, "Cliente requerido");
        if (map.putIfAbsent(cliente.getId(), cliente) != null) { 
            throw new IllegalStateException("Ya existe un cliente con id=" + cliente.getId());
        }
        this.cliente = cliente;
    }

    @Override
    public void actualizar(Cliente cliente) {
        Objects.requireNonNull(cliente, "Cliente requerido");
        String id = cliente.getId();
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("No existe cliente con id=" + id);
        }
        map.put(id, cliente);
        this.cliente = cliente;
    }

    @Override
    public void eliminar(String id) {
        Objects.requireNonNull(id, "Id requerido");
        Cliente eliminado = map.remove(id);
        if (eliminado == null) {
            throw new IllegalArgumentException("No existe cliente con id=" + id);
        }
        mapDeleted.add(eliminado);
        if (cliente != null && cliente.getId().equals(id)) {
            this.cliente = null;
        }
    }

    @Override
    public Cliente buscar(String id) { 
        Objects.requireNonNull(id, "Id requerido");
        cliente = map.get(id);
        return cliente; 
    }

    @Override
    public boolean existe(String id) {
        Objects.requireNonNull(id, "Id requerido");
        return map.containsKey(id);
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(map.values());
    }
    
    public List<Cliente> listarEliminados(){
        return Collections.unmodifiableList(this.mapDeleted);
    }
}
