package com.ws.service;

import java.util.List;
import java.util.Map;

import com.ws.entity.Irctc;

public interface IrctcService 
{
public String addIrctc(Irctc irctc);

public List<Irctc> getAllIrctc(Long length);

public Irctc getIrctcById(Integer id);

public Irctc updateIrctc(Irctc irctc,Integer id);

public Irctc updateIrctcByFields(Map<String,Object>fields,Integer id);

public boolean deleteIrctcById(Integer id);

}



