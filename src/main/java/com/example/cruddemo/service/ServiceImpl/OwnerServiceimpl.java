package com.example.cruddemo.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Owner;
import com.example.cruddemo.model.Users;
import com.example.cruddemo.repository.OwnerRepository;
import com.example.cruddemo.service.OwnerService;

@Service
public class OwnerServiceimpl implements OwnerService{

	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	UserServiceimpl serviceimpl;

	@Override
	public Owner getOwner(String id) {
		Owner owner=  ownerRepository.findById(id).orElse(null);
		if(owner==null)
		{
			return null;
		}
		return owner;
	}

	@Override
	public String createOwner(String userId) {
		Users user= serviceimpl.getUserById(userId);
		if(user==null)
		{
			return "";
		}
		Owner owner=new Owner();
		owner.setFirstName(user.getU_fn());
		owner.setLastName(user.getU_ln());
		owner.setAddress(user.getU_addr());
		owner.setE_mail(user.getU_em());
		owner.setMobile_num(user.getU_mn());
		
		Owner saved= ownerRepository.save(owner);
		serviceimpl.deleteById(userId);
		return saved.getMobile_num();
		
	}
	

}
