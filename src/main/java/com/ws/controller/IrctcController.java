package com.ws.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.entity.Irctc;
import com.ws.service.IrctcService;

@RestController
@RequestMapping("/irctc")

public class IrctcController 
{
	@Autowired
private IrctcService irctcSer;
	
//================localhost:8080/irctc/add---->POST
	
@PostMapping("/add")
public ResponseEntity<String> saveIrctcToDB(@RequestBody Irctc irctc)
{
	String addIrctc = irctcSer.addIrctc(irctc);
	return new ResponseEntity<> (addIrctc, HttpStatus.CREATED);
}


//================//localhost:8080/irctc/2

@GetMapping("/{id}")
public ResponseEntity<Irctc> getIrctc(@PathVariable Integer id)
{
	Irctc irctcById = irctcSer.getIrctcById(id);
	return new ResponseEntity<>(irctcById,HttpStatus.OK);
}

//================//localhost:8080/irctc/all?length=2----->Getall

@GetMapping("/all")
public ResponseEntity<List<Irctc>> getAllIrctc(@RequestParam(name="length",required = false)Long length)
{
	List<Irctc> irctclList = irctcSer.getAllIrctc(length);
	if(length==null)
	{
		return new ResponseEntity<>(irctclList.stream().limit(1l).collect(Collectors.toList()),HttpStatus.OK);
	}else
	{
		return new ResponseEntity<>(irctclList.stream().limit(length).collect(Collectors.toList()),HttpStatus.OK);
	}
}


//================//localhost:8080/irctc/update/2------>PUT

@PutMapping("/update/{id}")
public ResponseEntity<Irctc> updateIrctc(@RequestBody Irctc irctc,@PathVariable Integer id)
{
Irctc updateIrctc = irctcSer.updateIrctc(irctc,id);
return new ResponseEntity<>(updateIrctc,HttpStatus.ACCEPTED);
}

//================//localhost:8080/irctc/update/2------>PATCH

@PatchMapping("/patch/{id}")
public ResponseEntity<Irctc> updateIrctcByFields(@RequestBody Map<String,Object> fields,@PathVariable Integer id)
{
Irctc updateIrctcByFields = irctcSer.updateIrctcByFields(fields, id);
return new ResponseEntity<> (updateIrctcByFields, HttpStatus.ACCEPTED);
}

//================//localhost:8080/irctc/delete/2---->DELETE

@DeleteMapping("/delete/{id}")
public ResponseEntity<String>deleteIrctc(@PathVariable Integer id)
{
boolean isDeleted= irctcSer.deleteIrctcById(id);
String msg=" ";
if(isDeleted)
{
	msg="Ticket booking is deleted";
}
else {
	msg="***Ticket booking is not deleted***";
}
return new ResponseEntity<>(msg,HttpStatus.OK);
}
}