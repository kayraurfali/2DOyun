/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package oyun.yapi;

import java.awt.Graphics;
import java.util.LinkedList;
import oyun.yapi.OyunNesnesi;
/**
 *
 * @author kayra
 */
public class Isleyici {
    
    public LinkedList<OyunNesnesi> nesneler = new LinkedList<>();
    
    private OyunNesnesi geciciNesne;
    
    public void tick(){
        for(int i = 0; i < nesneler.size(); i++){
            geciciNesne = nesneler.get(i);
            
            geciciNesne.tick(nesneler);
        }
    }
    
    public void render(Graphics g){
        
        for(int i = 0; i < nesneler.size(); i++){
            geciciNesne = nesneler.get(i);
            
            geciciNesne.render(g);
        }
    }
    
    public void nesneEkle(OyunNesnesi nesne){
        nesneler.add(nesne);
    }
    
    public void nesneSil(OyunNesnesi nesne){
        nesneler.remove(nesne);
    }
}
