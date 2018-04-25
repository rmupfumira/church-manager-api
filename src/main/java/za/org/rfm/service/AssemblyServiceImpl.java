package za.org.rfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.rfm.dao.AssemblyRepository;
import za.org.rfm.entity.Assembly;

@Service
public class AssemblyServiceImpl implements AssemblyService {
	@Autowired
	private AssemblyRepository assemblyDAO;
	@Override
	public Assembly getAssemblyById(Integer assemblyId) {
		Assembly obj = assemblyDAO.getAssemblyById(assemblyId);
		return obj;
	}	
	@Override
	public List<Assembly> getAllAssemblies(){
		return assemblyDAO.getAllAssemblies();
	}
	@Override
	public synchronized boolean addAssembly(Assembly assembly){
		assemblyDAO.addAssembly(assembly);
  		return true;
	}
	@Override
	public void updateAssembly(Assembly assembly) {
		assemblyDAO.updateAssembly(assembly);
	}
	@Override
	public void deleteAssembly(Integer assemblyId) {
		assemblyDAO.deleteAssembly(assemblyId);
	}
}
