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
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author kayra
 */
public abstract class OyunNesnesi {
    protected float x, y;
    protected float hizX = 0, hizY = 0;
    protected NesneID id;
    
    @SuppressWarnings("FieldMayBeFinal")
    protected boolean   dusus = true,
                        ziplayis = true;

    public OyunNesnesi(float x, float y, NesneID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<OyunNesnesi> nesne);
    public abstract void render(Graphics g);
    public abstract Rectangle sinirlar();
    
    public float getX() {
        return x;
    }

    public boolean getDusus() {
        return dusus;
    }

    public void setDusus(boolean dusus) {
        this.dusus = dusus;
    }

    public boolean getZiplayis() {
        return ziplayis;
    }
    
    public void setZiplayis(boolean ziplayis){
        this.ziplayis = ziplayis;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHizX() {
        return hizX;
    }

    public float getHizY() {
        return hizY;
    }

    public void setHizX(float x) {
        this.hizX = x;
    }

    public void setHizY(float y) {
        this.hizY = y;
    }

    public NesneID getId() {
        return id;
    }
    
}
