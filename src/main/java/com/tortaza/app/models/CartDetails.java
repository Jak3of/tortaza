package com.tortaza.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_details")
public class CartDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;
	
	@ManyToOne
    @JoinColumn(name="cartId")
    private Cart cart;

    @OneToOne
    @JoinColumn(name="id_producto")
	private Producto producto;
    
    private Integer cantidad;
    
    private double subtotal;

	public CartDetails(Integer id_detalle, Cart cart, Producto producto, Integer cantidad, double subtotal) {
		super();
		this.id_detalle = id_detalle;
		this.cart = cart;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public CartDetails() {
		super();
	}

	public Integer getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
    
    
	
}
