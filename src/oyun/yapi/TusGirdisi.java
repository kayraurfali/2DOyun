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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author kayra
 */
public class TusGirdisi extends KeyAdapter{
    
    Isleyici isleyici;
    
    public TusGirdisi(Isleyici isleyici){
        this.isleyici = isleyici;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int tus = e.getKeyCode();
        
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            
            OyunNesnesi nesne = isleyici.nesneler.get(i);
            
            if(nesne.getId() == NesneID.Oyuncu) {
                if(tus == KeyEvent.VK_D) nesne.setHizX(5);
                if(tus == KeyEvent.VK_A) nesne.setHizX(-5);
                if(tus == KeyEvent.VK_SPACE && !nesne.getZiplayis()){
                    nesne.setZiplayis(true);
                    nesne.setHizY(-10);
                }
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int tus = e.getKeyCode();
        
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            OyunNesnesi nesne = isleyici.nesneler.get(i);
            
            if(nesne.getId() == NesneID.Oyuncu) {
                if(tus == KeyEvent.VK_D) nesne.setHizX(0);
                if(tus == KeyEvent.VK_A) nesne.setHizX(0);
            }
        }
    }
}
