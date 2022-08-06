package com.tortaza.app.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tortaza.app.models.Cart;

public interface ICart extends JpaRepository<Cart, Integer> {

}
