package com.kishor.business_accounting_system.service;

import java.util.List;

import com.kishor.business_accounting_system.entity.Mahajan;

public interface MahajanService{

	Mahajan saveMahajan(Mahajan mahajan);

	List<Mahajan> getAllMahajans();

	Mahajan getMahajanById(Long id);
	
}