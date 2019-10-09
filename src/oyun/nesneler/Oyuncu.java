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
@SuppressWarnings("FieldMayBeFinal")
public class Oyuncu extends OyunNesnesi{
    
    private float genislik = 32, 
                  yukseklik = 64,
                  yercekimi = 0.4f;
    
    
    private final float MAX_HIZ = 10;
    
    public static float xPos, yPos;
    
    private Isleyici isleyici;
    
    Doku doku = Oyun.olayCagir();
    
    private Animasyon yuruyusIleri;
    private Animasyon yuruyusGeri;
    public static int facing;
    
    public Oyuncu(float x, float y,Isleyici isleyici, NesneID id) {
        super(x, y, id);
        this.isleyici = isleyici;
        
        xPos = x;
        yPos = y;
        
        yuruyusIleri = new Animasyon(5, doku.dokularSag);
        yuruyusGeri = new Animasyon(5, doku.dokularSol);
    }

    @Override
    public void tick(LinkedList<OyunNesnesi> nesne) {
        
        x += hizX;
        y += hizY;
        
        if(hizX < 0) facing = -1;//sol
        if(hizX > 0) facing = 1;//sağ
        
        if(dusus || ziplayis){
           hizY += yercekimi;
           
           if(hizY > MAX_HIZ){
               hizY = MAX_HIZ;
           }
        }
        
        carpisma(nesne);
        
        yuruyusIleri.animasyonCalistir();
        yuruyusGeri.animasyonCalistir();
        
        
    }
    
    public void carpisma(LinkedList<OyunNesnesi> nesne){
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            OyunNesnesi gnesne = isleyici.nesneler.get(i);
            
            if(gnesne.getId() == NesneID.Platform || gnesne.getId() == NesneID.Wall || gnesne.getId() == NesneID.Pasta){
                //Ust
                if(sinirlarUst().intersects(gnesne.sinirlar())){
                    y = gnesne.getY() + 32;
                    hizY = 0;
                }
                //Alt
                if(sinirlar().intersects(gnesne.sinirlar())){
                    y = gnesne.getY() - yukseklik;
                    hizY = 0;
                    dusus = false;
                    ziplayis = false;                    
                }else{
                    dusus = true;
                }
                //Sag
                if(sinirlarSag().intersects(gnesne.sinirlar())){
                    x = gnesne.getX() - genislik;
                }
                //Sol
                if(sinirlarSol().intersects(gnesne.sinirlar())){
                    x = gnesne.getX() + 32;
                }
            }
            
        }
    }
    
    @Override
    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    public void render(Graphics g) {
        if (hizX != 0) {
            if(facing == 1) yuruyusIleri.render(g, (int) x, (int) y, 32, 64);
            if(facing == -1) yuruyusGeri.render(g, (int) x, (int) y, 32, 64);
        } else {
            switch (facing) {
                case 1:
                    g.drawImage(doku.oyuncuSp[0], (int) x, (int) y, 32, 64, null);
                    break;
                case -1:
                    g.drawImage(doku.oyuncuSp[13], (int) x, (int) y, 32, 64, null);
                    break;
                default:
                    g.drawImage(doku.oyuncuSp[0], (int) x, (int) y, 32, 64, null);
                    break;
            }
        }
    }

    @Override
    public Rectangle sinirlar() {
        return new Rectangle((int) ((int)x+((genislik/2)/2)), (int) ((int)y+(yukseklik/2)), (int)genislik/2, (int)yukseklik/2);
    }
    
    public Rectangle sinirlarUst() {
        return new Rectangle((int) ((int)x+(genislik/2)/2), (int)y, (int)genislik/2, (int)yukseklik/2);
    }
    
    
    public Rectangle sinirlarSag() {
        return new Rectangle((int) ((int)x+genislik-5), (int)y+5, (int)5, (int)yukseklik-10);
    }
    
    public Rectangle sinirlarSol() {
        return new Rectangle((int)x, (int)y+5, 5, (int)yukseklik-10);
    }   
}