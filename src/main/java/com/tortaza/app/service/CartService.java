package com.tortaza.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tortaza.app.models.Cart;
import com.tortaza.app.services.ICart;
import com.tortaza.app.services.ICartService;

@Transactional
@Service
public class CartService implements ICartService{
	
	@Autowired
	private ICart dato;
	
	
	@Override
    public List<Cart> listar() {
        return (List<Cart>) dato.findAll();
      
    }

    @Override
    public Optional<Cart> listarId(int id) {
    	
        return dato.findById(id);
    }

    @Override
    public int guardar(Cart p) {
        int res = 0;
        Cart producto = dato.save(p);
        if (!producto.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
    	
    }

	@Override
	public List<Cart> findAll() {
		return (List<Cart>) dato.findAll();
	}
}
