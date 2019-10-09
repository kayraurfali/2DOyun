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

import java.awt.image.BufferedImage;

/**
 *
 * @author kayra
 */
public class Doku {
    
    SpriteSayfasi blokS, oyuncuS, cake;
    ResimYukleyici yukleyici = new ResimYukleyici();
    
    @SuppressWarnings("FieldMayBeFinal")
    public  BufferedImage   bloklar = null,
                            oyuncu = null,
                            pasta = null;
    
    public BufferedImage[] pastaDoku = new BufferedImage[1];
    public BufferedImage[] zemin = new BufferedImage[2];
    public BufferedImage[] altin = new BufferedImage[9];
    
    public BufferedImage[] lavDurgun = new BufferedImage[3];
    public BufferedImage[] lavHareketli = new BufferedImage[3];
    
    public BufferedImage[] oyuncuSp = new BufferedImage[14];
    
    public BufferedImage[] dokularSag = new BufferedImage[7];
    public BufferedImage[] dokularSol = new BufferedImage[7];
    
    
    public Doku(){
        bloklar = yukleyici.levelYukle("/oyun/res/blok.png");
        oyuncu = yukleyici.levelYukle("/oyun/res/oyuncu.png");
        pasta = yukleyici.levelYukle("/oyun/res/birthday.png");
        
        blokS = new SpriteSayfasi(bloklar);
        oyuncuS = new SpriteSayfasi(oyuncu);
        cake = new SpriteSayfasi(pasta);
        
        dokuGetir();
    }

    private void dokuGetir() {
        zemin[0] = blokS.resimYakala(1, 1, 32, 32);
        zemin[1] = blokS.resimYakala(2, 1, 32, 32);
        
        //////////////////// Lav
        for(int i = 0; i < 3; i++){
            lavDurgun[i] = blokS.resimYakala(i+6, 1, 32, 32);
        }
        
        for(int i = 0; i < 3; i++){
            lavHareketli[i] = blokS.resimYakala(i+3, 1, 32, 32);
        }
        ////////////////////////
        
        ////////////////////Oyuncu hareketleri
        for(int i = 0; i < oyuncuSp.length/2; i++){
            oyuncuSp[i] = oyuncuS.resimYakala(i+1, 1, 32, 64);
            oyuncuSp[i+7] = oyuncuS.resimYakala(i+14, 1, 32, 64);
        }
        ////////////////////////

        
        
        
        ////////////////////altin
        for(int i = 0; i < altin.length - 3; i++){
            altin[i] = blokS.resimYakala(i+1, 2, 32, 32);
        }
        
        for(int i = 0; i < 3; i++){
            altin[i+6] = blokS.resimYakala(i+1, 3, 32, 32);
        }
        ///////////////////
        
        
        ////////////////////Oyuncu hareketleri
        for(int i = 0; i < oyuncuSp.length/2; i++){
            dokularSag[i] = oyuncuSp[i];
        }
        for(int i = 7; i < oyuncuSp.length; i++){
            dokularSol[i-7] = oyuncuSp[i];
        }
        ////////////////////////
    }
}
