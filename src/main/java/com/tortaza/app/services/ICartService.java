package com.tortaza.app.services;

import java.util.List;
import java.util.Optional;

import com.tortaza.app.models.Cart;

public interface ICartService {

	public List<Cart> listar();

    public Optional<Cart> listarId(int id);
    

    public int guardar(Cart c);

    public void eliminar(int id);
    
    
    public List<Cart> findAll();
}
