
package opintoapp.ui;

import opintoapp.domain.StudyService;

public class UiController {
    
    protected StudyService service;
    protected OpintoAppMain application;
    
    public void setService(StudyService service){
        this.service = service;
    }
    
    public void setApplication(OpintoAppMain application){
        this.application = application;
    }
    
}
