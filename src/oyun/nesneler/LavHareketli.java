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
public class LavHareketli extends OyunNesnesi {

    Doku doku = Oyun.olayCagir();
    Animasyon hareket;
    Isleyici isleyici;
    
    @SuppressWarnings("FieldMayBeFinal")
    private int tip;
    
    public LavHareketli(int x, int y, NesneID id) {
        super(x, y, id);
        
        hareket = new Animasyon(5, doku.lavHareketli[0], doku.lavHareketli[1], doku.lavHareketli[2]);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
        hareket.animasyonCalistir();
    }

    @Override
    public void render(Graphics g) {
        hareket.render(g, (int)x, (int)y, 64, 64);
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

}
