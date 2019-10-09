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

/**
 *
 * @author kayra
 */
public class Kamera {
    
    private float x, y;

    public Kamera(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void tick(OyunNesnesi oyuncu){
        //x += (-oyuncu.getX() - x - 5) * 0.05;
        x += -oyuncu.getX() - x + 500;
        y = -oyuncu.getY() + 700;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
}
