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
public class LavDurgun extends OyunNesnesi {

    Doku doku = Oyun.olayCagir();
    Animasyon durgun;
    Isleyici isleyici;
    
    public LavDurgun(float x, float y, NesneID id) {
        super(x, y, id);
        
        durgun = new Animasyon(5, doku.lavDurgun[0], doku.lavDurgun[1], doku.lavDurgun[2]);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
        durgun.animasyonCalistir();
    }

    @Override
    public void render(Graphics g) {
            durgun.render(g, (int)x, (int)y, 64, 64);
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

}
