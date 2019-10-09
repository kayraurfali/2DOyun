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
import oyun.pencere.Oyun;
import oyun.yapi.Doku;
import oyun.yapi.NesneID;
import oyun.yapi.OyunNesnesi;
/**
 *
 * @author kayra
 */
public class Pasta extends OyunNesnesi {

    Doku doku = Oyun.olayCagir();
    
    public Pasta(float x, float y, NesneID id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(doku.pasta,(int)x,(int)y,112,112, null);
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 128, 128);
    }

}
