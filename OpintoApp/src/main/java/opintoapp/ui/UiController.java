
package opintoapp.ui;

import opintoapp.domain.StudyService;

/**
 * Yläluokka FXML-tiedostojen kontrollereille.
 * 
 */
public class UiController {
    
    protected StudyService service;
    protected OpintoAppMain application;
    
    /**
     * Asettaa kontrollerille sovelluslogiikkaluokan tapahtumien käsittelyä varten.
     * @param service 
     */
    public void setService(StudyService service){
        this.service = service;
    }
    
    /**
     * Asettaa kontrollerille main-luokan implementaation scene-olioiden vaihtoa varten.
     * @param application 
     */
    public void setApplication(OpintoAppMain application){
        this.application = application;
    }
    
}
