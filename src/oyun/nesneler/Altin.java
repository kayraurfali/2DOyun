/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package oyun.nesneler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import oyun.yapi.Isleyici;
import oyun.pencere.Oyun;
import oyun.yapi.Animasyon;
import oyun.yapi.Doku;
import oyun.yapi.NesneID;
import oyun.yapi.OyunNesnesi;
/**
 *
 * @author kayra
 */
public class Altin extends OyunNesnesi {

    Doku doku = Oyun.olayCagir();
    Animasyon donus;
    Isleyici isleyici;
    
    
    public Altin(float x, float y, NesneID id) {
        super(x, y, id);
        
        donus = new Animasyon(5, doku.altin[0], doku.altin[1], doku.altin[2], doku.altin[3], doku.altin[4], doku.altin[5], doku.altin[6], doku.altin[7], doku.altin[8]);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
        donus.animasyonCalistir();
    }

    @Override
    public void render(Graphics g) {
        donus.render(g, (int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

}
