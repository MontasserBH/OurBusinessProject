package ourbusinessproject;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SoF on 06/11/2017.
 */
@Service
@Transactional
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Project project){
        Enterprise enterprise = project.getEnterprise();
        if (enterprise != null){
            enterprise.addProjects(project);
            save(enterprise);
        }
        this.entityManager.persist(project);
    }

    public void save(Enterprise enterprise){
        this.entityManager.persist(enterprise);
    }

    public Project findProjectById(Long anid){
        return this.entityManager.find(Project.class, anid);
    }

    public Enterprise findEnterpriseById(Long anid){
        return this.entityManager.find(Enterprise.class, anid);
    }

    public List<Project> findAllProjects(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        CriteriaQuery<Project> all = cq.select(root);
        all.orderBy(cb.asc(root.get("title")));
        TypedQuery<Project> res = entityManager.createQuery(all);

        return res.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
