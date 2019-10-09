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

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author kayra
 */
public class Pencere{
    
    
    public Pencere(int g, int y, String baslik, Oyun o, boolean fs){
        o.setPreferredSize(new Dimension(g,y));
        o.setMinimumSize(new Dimension(g,y));
        o.setMaximumSize(new Dimension(g,y));
        
        JFrame f = new JFrame(baslik);
        f.add(o);
        //f.pack();
        if(fs){
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
            f.setUndecorated(true);
        }else{
            f.pack();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setTitle(baslik);
        }
        
        f.setVisible(true);
        o.start();
    }
}
