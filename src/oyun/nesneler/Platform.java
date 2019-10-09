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
public class Platform extends OyunNesnesi {

    Doku doku = Oyun.olayCagir();
    
    @SuppressWarnings("FieldMayBeFinal")
    private int tip;
    
    public Platform(float x, float y, int tip, NesneID id) {
        super(x, y, id);
        this.tip = tip;
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(doku.zemin[tip],(int)x,(int)y,32,32, null);
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

}
