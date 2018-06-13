/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Saplic 01
 */
@ManagedBean
public class Slideshow {
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            images.add("sld" + i + ".jpg");
            
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
