package za.org.rfm.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import za.org.rfm.entity.Assembly;

@Transactional
@Repository
public class AssemblyRepositoryImpl implements AssemblyRepository {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Assembly getAssemblyById(Integer assemblyId) {
		return entityManager.find(Assembly.class, assemblyId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Assembly> getAllAssemblies() {
		String hql = "FROM Assembly as atcl ORDER BY atcl.assemblyId";
		return (List<Assembly>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addAssembly(Assembly assembly) {
		entityManager.persist(assembly);
	}
	@Override
	public void updateAssembly(Assembly assembly) {
		Assembly assembly1 = getAssemblyById(assembly.getId());
		assembly1.setName(assembly.getName());
		entityManager.flush();
	}
	@Override
	public void deleteAssembly(Integer assemblyId) {
		entityManager.remove(getAssemblyById(assemblyId));
	}
	@Override
	public boolean assemblyExists(String title, String category) {
		String hql = "FROM Assembly as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
