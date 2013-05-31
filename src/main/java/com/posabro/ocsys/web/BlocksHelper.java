/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

/**
 *
 * @author Carlos Juarez
 */
public class BlocksHelper {
    
     public static PdfPCell getNoBorderCell() {
        PdfPCell cell = new PdfPCell(new Phrase(Chunk.NEWLINE));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static Phrase getPhrase(Font font, String... contents) {
        return getNarrowPhrase(font, 1, contents);
    }
     
    public static Phrase getNarrowPhrase(Font font, float scaling, String... contents) {
        Phrase phrase = new Phrase();
        for (int i = 0; i < contents.length; i++) {
            if(contents[i]!=null){
                Chunk chunk = new Chunk(contents[i], font);
                chunk.setHorizontalScaling(scaling);
                phrase.add(chunk);
                if (i < contents.length - 1) {
                    phrase.add(Chunk.NEWLINE);
                }
            }
        }
        return phrase;
    }
    
}
