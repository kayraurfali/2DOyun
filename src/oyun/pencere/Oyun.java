/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package oyun.pencere;

import oyun.yapi.Isleyici;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import oyun.nesneler.Altin;
import oyun.nesneler.Oyuncu;
import oyun.nesneler.Platform;
import oyun.nesneler.LavDurgun;
import oyun.nesneler.LavHareketli;
import oyun.nesneler.Pasta;
import oyun.nesneler.Wall;
import oyun.yapi.Doku;
import oyun.yapi.Kamera;
import oyun.yapi.ResimYukleyici;
import oyun.yapi.NesneID;
import oyun.yapi.OyunNesnesi;
import oyun.yapi.TusGirdisi;



/**
 *
 * @author kayra
 */
public class Oyun extends Canvas implements Runnable{
    
    public static int GENISLIK, YUKSEKLIK;

    private boolean running = false;
    private Thread thread;
    
    private BufferedImage level = null;
    
    Isleyici isleyici;
    Kamera kam;
    static Doku doku;
    private boolean yuklendi;
    private long baslangic;
    private boolean goldPicked;
    private boolean jumped;
    private boolean theEnd;
    
    private void init() {
        
        GENISLIK = getWidth();
        YUKSEKLIK = getHeight();
        
        doku = new Doku();
        
        ResimYukleyici yukleyici = new ResimYukleyici();
        
        level = yukleyici.levelYukle("/oyun/res/level.png");
        
        isleyici = new Isleyici();
        
        kam = new Kamera(0,0);
        
//        isleyici.nesneEkle(new Oyuncu(100, 100, isleyici , NesneID.Oyuncu));        
//        isleyici.bolumOlustur();

        bolumuYukle(level);
        
        this.addKeyListener(new TusGirdisi(isleyici));
    }
    
    @SuppressWarnings("override")
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; //1 tick kaç saniyede
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while(delta >= 1){
                        tick();
                        updates++;
                        delta--;
                }
                render();
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                        timer += 1000;
                        //System.out.println("FPS: " + frames + " TICKS: " + updates);
                        frames = 0;
                        updates = 0;
                }
        }
    }
    
    public synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public static void main(String[] args) {
        new Pencere(640, 640, "Your Worst Present", new Oyun(), true);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;
        //////////////////////////////////////////////////////////
        
        //Çizim işlemleri
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(yuklendi && System.currentTimeMillis() - baslangic > 3000 && System.currentTimeMillis() - baslangic < 5500){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Welcome to your worst b-day present.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 100);
        }
     
        if(yuklendi && System.currentTimeMillis() - baslangic > 23000 && System.currentTimeMillis() - baslangic < 26000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Ok, ok, I'll cut the bs now. Fine. Let's continue to game.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
            for(int i = 0; i < isleyici.nesneler.size(); i++){
                OyunNesnesi gNesne = isleyici.nesneler.get(i);
                
                if(gNesne.getId() == NesneID.Wall){
                    isleyici.nesneSil(gNesne);
                }
            }
        }
        
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            OyunNesnesi n = isleyici.nesneler.get(i);
            
            if(n.getId() == NesneID.Oyuncu && n.getX() >= 2105 && n.getX() < 2556 && !goldPicked){
                g.setColor(Color.white);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("I want you to pick that gold up for me please", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 130);
            }
            
            if(n.getId() == NesneID.Oyuncu && n.getX() >= 2556 && n.getX() <= 2571 && !goldPicked){
                baslangic = System.currentTimeMillis();
                goldPicked = true;
            }
            
            if(n.getId() == NesneID.Oyuncu && goldPicked && System.currentTimeMillis() - baslangic < 3000 && !jumped){
                g.setColor(Color.white);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("Of course you won't be able to pick it up.", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 130);
                yuklendi = false;
            }
            
            if(n.getId() == NesneID.Oyuncu && n.getX() >= 6330 && n.getX() < 6600 && !jumped){
                baslangic = 9999999L;
                g.setColor(Color.white);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("Here, i put a block to make this game entertaining for ya.", (int)Oyuncu.xPos - 450, (int)Oyuncu.yPos - 130);
            }
            
            if(n.getId() == NesneID.Oyuncu && n.getX() >= 6690 && n.getX() < 6700 && !jumped){
                jumped = true;
                baslangic = System.currentTimeMillis();
            }
            
            if(n.getId() == NesneID.Oyuncu && jumped && System.currentTimeMillis() - baslangic < 3000){
                g.setColor(Color.white);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("Good job. You just jumped.", (int)Oyuncu.xPos - 550, (int)Oyuncu.yPos - 130);                
            }
            
            if(n.getId() == NesneID.Oyuncu && n.getX() >= 8720 && n.getX() < 9100 && !theEnd){
                baslangic = 9999999L;
                g.setColor(Color.white);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("Happy birthday Liliya,.", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 400);
                g.drawString("and know that,", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 370);
                g.drawString("I'm really sorry", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 340);
                g.drawString("this is not even a game :D.", (int)Oyuncu.xPos - 500, (int)Oyuncu.yPos - 310);
            }
        }
        
        if(goldPicked && !jumped && System.currentTimeMillis() - baslangic > 3000 && System.currentTimeMillis() - baslangic < 7000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("But I have made you see how much of a golddigger you are.", (int)Oyuncu.xPos - 600, (int)Oyuncu.yPos - 110);            
        }
        
        if(goldPicked && !jumped && System.currentTimeMillis() - baslangic > 8000 && System.currentTimeMillis() - baslangic < 12000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("By the way, did I mention that you basically", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
            g.drawString("cannot die in this game. (Unless you find a bug)", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 80);   
        }
        
        if(goldPicked && !jumped  && System.currentTimeMillis() - baslangic > 12000 && System.currentTimeMillis() - baslangic < 15000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Because I know that you're not a hardcore gamer.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
        }
        
        if(goldPicked && !jumped  && System.currentTimeMillis() - baslangic > 15000 && System.currentTimeMillis() - baslangic < 19000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Neither I am a great game developer.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
        }
        
        if(goldPicked && !jumped  && System.currentTimeMillis() - baslangic > 19000 && System.currentTimeMillis() - baslangic < 22000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Anyways, let's just continue.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
        }
        
        if(jumped && !theEnd && System.currentTimeMillis() - baslangic > 3500 && System.currentTimeMillis() - baslangic < 6000){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("We are approaching the end now.", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
        }
        
        if(jumped && !theEnd && System.currentTimeMillis() - baslangic > 6000 && System.currentTimeMillis() - baslangic < 11500){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Take this as a re-gift for the one", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 110);
            g.drawString("that you gave me a couple months ago", (int)Oyuncu.xPos - 50, (int)Oyuncu.yPos - 80);
        }
        
        
        ///////////////////////////////////////
        g2.translate(kam.getX(), kam.getY());
                //Kamera tarafından etkilenecek değişkenler
                
                isleyici.render(g);
                
                /////////////////////////
        g2.translate(-kam.getX(), -kam.getY());
        ///////////////////////////////////////
        ////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    private void bolumuYukle(BufferedImage bolum){
        
        int g = bolum.getWidth();
        int y = bolum.getHeight();
        
        for(int xx = 0; xx < y; xx++){
            for(int yy = 0 ; yy < g; yy++){
                int pixel =  bolum.getRGB(xx, yy);
                int kirmizi = (pixel >> 16) & 0xff;
                int yesil = (pixel >> 8) & 0xff;
                int mavi = (pixel) & 0xff;
                
                if(kirmizi == 160 && yesil == 85 && mavi == 53){ //Toprak
                    isleyici.nesneler.add(new Platform(xx*32, yy*32, 0, NesneID.Platform));
                }
                else if(kirmizi == 17 && yesil == 158 && mavi == 45){ //Çimen
                    isleyici.nesneler.add(new Platform(xx*32, yy*32, 1, NesneID.Platform));
                }
                if(kirmizi == 255 && yesil == 248 && mavi == 58){
                    isleyici.nesneler.add(new Altin(xx*32, yy*32, NesneID.Altin));
                }
                if(kirmizi == 255 && yesil == 0 && mavi == 0){
                    isleyici.nesneler.add(new LavHareketli(xx*32, yy*32, NesneID.Lav));
                }else if(kirmizi == 190 && yesil == 0 && mavi == 0){
                    isleyici.nesneler.add(new LavDurgun(xx*32, yy*32, NesneID.Lav));
                }
                if(kirmizi == 0 && yesil == 0 && mavi == 255){
                    isleyici.nesneler.add(new Oyuncu(xx*32, yy*32, isleyici, NesneID.Oyuncu));
                }
                if(kirmizi == 255 && yesil == 255 && mavi == 255){
                    isleyici.nesneler.add(new Wall(xx*32, yy*32, NesneID.Wall));
                }
                if(kirmizi == 255 && yesil == 0 && mavi == 110){
                    isleyici.nesneler.add(new Pasta(xx*32, yy*32, NesneID.Pasta));
                }
            }
        }
        
        yuklendi = true;
        baslangic = System.currentTimeMillis();
    }

    private void tick() {
        isleyici.tick();
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            OyunNesnesi n = isleyici.nesneler.get(i);
            
            if(n.getId() == NesneID.Oyuncu){
                kam.tick(n);
            }            
        }
    }
    
    public static Doku olayCagir(){
        return doku;
    }

}
