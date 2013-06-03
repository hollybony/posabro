/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Provides fonts with different styles and sizes
 * 
 * @author Carlos Juarez
 */
public class FontsHelper {

    public static final Font BOLD_ITALIC_FONT = new Font();
        
    public static final Font WHITE_ITALIC_FONT = new Font();
    
    public static final Font WHITE_BOLD_FONT = new Font();
    
    public static final Font BOLD_FONT = new Font();
    
    public static final Font BOLD_UNDERLINED_FONT = new Font();
    
    public static final Font BOLD_12_5_FONT = new Font();
    
    public static final Font BOLD_10_FONT = new Font();
    
    public static final Font BOLD_9_FONT = new Font();
    
    public static final Font NORMAL_7_FONT = new Font();
    
    public static final Font NORMAL_8_FONT = new Font();
    
    public static final Font NORMAL_9_FONT = new Font();
    
    public static final Font NORMAL_10_FONT = new Font();
    
    public static final Font NORMAL_11_FONT = new Font();
    
    public static final Font NORMAL_12_5_FONT = new Font();
    
    public static final Font UNDERLINED_8_FONT = new Font();
    
     
    
    static {
        BOLD_ITALIC_FONT.setStyle(Font.BOLDITALIC);
        
        WHITE_BOLD_FONT.setColor(BaseColor.WHITE);
        WHITE_BOLD_FONT.setStyle(Font.BOLD);
        WHITE_BOLD_FONT.setSize(10);
        
        WHITE_ITALIC_FONT.setColor(BaseColor.WHITE);
        WHITE_ITALIC_FONT.setStyle(Font.ITALIC);
        WHITE_ITALIC_FONT.setSize(10);
        
        BOLD_FONT.setStyle(Font.BOLD);
        
        BOLD_UNDERLINED_FONT.setStyle(Font.UNDERLINE | Font.BOLD);
        
        BOLD_12_5_FONT.setStyle(Font.BOLD);
        BOLD_12_5_FONT.setSize(12.5f);
        
        BOLD_10_FONT.setStyle(Font.BOLD);
        BOLD_10_FONT.setSize(10);
        
        BOLD_9_FONT.setStyle(Font.BOLD);
        BOLD_9_FONT.setSize(9);
        
        NORMAL_7_FONT.setSize(7);
        
        NORMAL_8_FONT.setSize(8);
        
        NORMAL_9_FONT.setSize(9);
        
        
        NORMAL_10_FONT.setSize(10);
        
        NORMAL_11_FONT.setSize(11);
        
        NORMAL_12_5_FONT.setSize(12.5f);
        
        UNDERLINED_8_FONT.setStyle(Font.UNDERLINE);
        UNDERLINED_8_FONT.setSize(8);
    }
}
