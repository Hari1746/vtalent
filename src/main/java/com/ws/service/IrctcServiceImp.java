package com.ws.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.ws.entity.Irctc;
import com.ws.exception.IrctcException;
import com.ws.repository.IrctcRepository;

@Service
public class IrctcServiceImp implements IrctcService
{
	@Autowired
	private IrctcRepository irctcrep;
	

//=========addIrctc--POST=========================================================
	@Override
	public String addIrctc(Irctc irctc) {
		Irctc irctcObj = irctcrep.save(irctc);
		if(irctcObj.getTicketno() != null)
		{
			return "Your Resevation is confirmed";
		}else
		return "Please try it again";
	}

//=========getAllIrctc--GETAll=========================================================	
	@Override
	public List<Irctc> getAllIrctc(Long length) {
		
		return irctcrep.findAll();
	}
	
	
//=========getIrctcById--GET/id=========================================================
	@Override
	public Irctc getIrctcById(Integer id) {

		return irctcrep.findById(id).orElseThrow(() ->new IllegalArgumentException("Your Reservation cannot be found"));
	}

//=========updateIrctc--PUT=========================================================
	@Override
	public Irctc updateIrctc(Irctc irctc, Integer id) {
		Irctc existingIrctc = irctcrep.findById(id).get();
//		existingIrctc.setUserid(irctc.getUserid());
		existingIrctc.setPassword(irctc.getPassword());
		existingIrctc.setName(irctc.getName());
		existingIrctc.setArrival(irctc.getArrival());
		existingIrctc.setDestination(irctc.getDestination());
		existingIrctc.setTicketno(irctc.getTicketno());
		return irctcrep.save(existingIrctc);
		
	}

//=========updateIrctcByFields--PATCH=========================================================
	@Override
	public Irctc updateIrctcByFields(Map<String, Object> fields, Integer id) {
		Optional<Irctc>existingIrctc = irctcrep.findById(id);
		if(existingIrctc.isPresent())
		{
			fields.forEach((key,value)->{
				Field field = ReflectionUtils.findRequiredField(Irctc.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingIrctc.get(), value);
				});
			return irctcrep.save(existingIrctc.get());
		}
		return null;
	}

//=========deleteIrctcById--DELETE=========================================================
	@Override
	public boolean deleteIrctcById(Integer id) {
		boolean status = false;
		Irctc irctcRecord = irctcrep.findById(id)
				.orElseThrow(() -> new IrctcException("Your record not found in the DB with ID:"+id));
		if(irctcRecord != null)
		{
			try {
				irctcrep.deleteById(id);
				status = true;
			}
			catch(IrctcException e)
			{
				System.out.println(e);
			}
		}
		return status;
	}

}




