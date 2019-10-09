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
import oyun.yapi.NesneID;
import oyun.yapi.OyunNesnesi;
/**
 *
 * @author kayra
 */
public class Wall extends OyunNesnesi {

    public Wall(float x, float y, NesneID id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
    }
    
    @Deprecated
    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

}
