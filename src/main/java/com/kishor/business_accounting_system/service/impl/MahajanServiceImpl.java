
package com.kishor.business_accounting_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kishor.business_accounting_system.entity.Mahajan;
import com.kishor.business_accounting_system.repository.MahajanRepository;
import com.kishor.business_accounting_system.service.MahajanService;

@Service
public class MahajanServiceImpl implements MahajanService {
	
	private final MahajanRepository mahajanrepository;
	
	public MahajanServiceImpl(MahajanRepository mahajanrepository) {
		this.mahajanrepository = mahajanrepository;
	}

	@Override
	public Mahajan saveMahajan(Mahajan mahajan) {
		// TODO Auto-generated method stub
		return mahajanrepository.save(mahajan);
	}

	@Override
	public List<Mahajan> getAllMahajans() {
		// TODO Auto-generated method stub
		return mahajanrepository.findAll();
	}

	@Override
	public Mahajan getMahajanById(Long id) {
		// TODO Auto-generated method stub
		return mahajanrepository.findById(id).orElseThrow(null);
	}
	
}