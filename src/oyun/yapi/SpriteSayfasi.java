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

import java.awt.image.BufferedImage;

/**
 *
 * @author kayra
 */
public class SpriteSayfasi {
    
    private BufferedImage sayfa;
    
    public SpriteSayfasi(BufferedImage sayfa){
        this.sayfa = sayfa;
    }
    
    public BufferedImage resimYakala(int sutun, int satir, int g, int y){
        BufferedImage resim = sayfa.getSubimage((sutun*g)-g, (satir*y)-y, g, y);
        return resim; 
    }
    
}
