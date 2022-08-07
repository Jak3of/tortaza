package com.tortaza.app.services;

import java.util.List;

import com.tortaza.app.models.Rol;

public interface IRolService {
	
	public abstract Rol findById (Integer Id);
	
	public abstract List<Rol> findAll();

}
