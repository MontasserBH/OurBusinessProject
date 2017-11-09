package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by SoF on 06/11/2017.
 */
@Service
@Transactional
public class InitializationService {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    private Enterprise enterprise1, enterprise2;
    private Project project1, project2, project3;

    public void init(){
        initEnterprises();
        initProjects();
    }

    public void initEnterprises(){
        enterprise1 = new Enterprise();
        enterprise1.setName("SQMyComp");
        enterprise1.setDescription("GGMy comp description");
        enterprise1.setContactEmail("hgcomp@com.com");
        enterprise1.setContactName("sqfcomp contact name");
        enterpriseProjectService.save(enterprise1);

        enterprise2 = new Enterprise();
        enterprise2.setName("MyComp");
        enterprise2.setDescription("My comp description");
        enterprise2.setContactEmail("comp@com.com");
        enterprise2.setContactName("comp contact name");
        enterpriseProjectService.save(enterprise2);
    }

    public void initProjects(){

        project1 = new Project("p1", "p3 description", enterprise1);
        try {
            enterpriseProjectService.save(project1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        project2 = new Project("p2", "p3 description", enterprise2);
        enterpriseProjectService.save(project2);

        project3 = new Project("p3", "p3 description", enterprise1);
        enterpriseProjectService.save(project3);
    }

    public Project getProject1E1() {
        return project1;
    }

    public Project getProject1E2() {
        return project2;
    }

    public Project getProject2E1() {
        return project3;
    }

    public Enterprise getEnterprise1() {
        return enterprise1;
    }

    public Enterprise getEnterprise2() {
        return enterprise2;
    }
}
