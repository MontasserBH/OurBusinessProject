package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;


/**
 * Created by SoF on 06/11/2017.
 */
@Component
@Transactional
public class Bootstrap {

    @Autowired
    private InitializationService initializationService;

    public Bootstrap() {
    }

    public Bootstrap(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @PostConstruct
    public void init() {
        try {
            this.initializationService.initEnterprises();
            this.initializationService.initProjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InitializationService getInitializationService() {
        return initializationService;
    }

    public void setInitializationService(InitializationService initializationService) {
        this.initializationService = initializationService;
    }
}
