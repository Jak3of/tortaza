package com.tortaza.app.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    
    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario",nullable=false,unique=true)
	private Usuario usuario;

    @OneToMany(mappedBy = "cart")
	private List<CartDetails> detalle;
    
    private double total;

	

	public Cart(Integer cartId, Usuario usuario, List<CartDetails> detalle, double total) {
		super();
		this.cartId = cartId;
		this.usuario = usuario;
		this.detalle = detalle;
		this.total = total;
	}

	public Cart() {
		super();
	}
	
	public void addProducto(CartDetails cardetalle) {
		this.detalle.add(cardetalle);
	}
	
	public void removeProducto(CartDetails cardetalle) {
		this.detalle.remove(cardetalle);
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public List<CartDetails> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CartDetails> detalle) {
		this.detalle = detalle;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    


}
