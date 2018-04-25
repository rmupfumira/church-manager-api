package za.org.rfm.service;

import java.util.List;

import za.org.rfm.entity.Assembly;

public interface AssemblyService {
     List<Assembly> getAllAssemblies();
     Assembly getAssemblyById(Integer assemblyId);
     boolean addAssembly(Assembly assembly);
     void updateAssembly(Assembly assembly);
     void deleteAssembly(Integer assemblyId);
}
