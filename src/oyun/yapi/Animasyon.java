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
import java.awt.image.BufferedImage;

/**
 *
 * @author kayra
 */
public class Animasyon{
    
    private final int hiz;
    private int kare;
    
    private int icerik = 0;
    private int sayi = 0;
    
    @SuppressWarnings("FieldMayBeFinal")
    private BufferedImage[] resimler;
    private BufferedImage anlikResim;
    
    public Animasyon(int hiz, BufferedImage... args){
        this.hiz = hiz;
        resimler =  new BufferedImage[args.length];
        System.arraycopy(args, 0, resimler, 0, args.length);
        kare = args.length;
    }
    
    public void animasyonCalistir(){
        icerik++;
        if(icerik > hiz){
            icerik = 0;
            sonrakiKare();
        }
    }

    private void sonrakiKare() {
        for(int i = 0; i < kare; i++){
            if(sayi == i){
                anlikResim = resimler[i];
            }
        }
        
        sayi++;
        
        if(sayi > kare){
            sayi = 0;
        }
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(anlikResim, x, y, null);
    }
    
    public void render(Graphics g, int x, int y, int genisletX, int genisletY){
        g.drawImage(anlikResim, x, y, genisletX, genisletY, null);
    }
}
