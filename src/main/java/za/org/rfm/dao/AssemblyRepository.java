package za.org.rfm.dao;
import java.util.List;

import za.org.rfm.entity.Assembly;

public interface AssemblyRepository {
    List<Assembly> getAllAssemblies();
    Assembly getAssemblyById(Integer assemblyId);
    void addAssembly(Assembly assembly);
    void updateAssembly(Assembly assembly);
    void deleteAssembly(Integer assemblyId);
    boolean assemblyExists(String title, String category);
}
 