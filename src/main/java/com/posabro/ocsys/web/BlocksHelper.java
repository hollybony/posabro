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
 * Helper class that provides convenience methods to create PDF blocks
 *
 * @author Carlos Juarez
 */
public class BlocksHelper {

    /**
     * Creates a new empty cell with no border
     * @return the cell
     */
    public static PdfPCell getNoBorderCell() {
        PdfPCell cell = new PdfPCell(new Phrase(Chunk.NEWLINE));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    /**
     * Constructs a phrase by using the parameter font and the array contents.
     * every string of the array is placed in a new line
     * 
     * @param font - the font used in the phrase
     * @param contents - the contents to be placed in the phrase
     * @return the phrase
     */
    public static Phrase getPhrase(Font font, String... contents) {
        return getNarrowPhrase(font, 1, contents);
    }

    /**
     * Constructs a phrase by using the parameter font, the array contents which
     * every string of the array is placed in a new line and the scaling parameter
     * is used to scale every chunk.
     * 
     * @param font - the font used in the phrase
     * @param scaling - the horizontal scaling to apply in every chunk
     * @param contents - the contents to be placed in the phrase
     * @return the phrase
     */
    public static Phrase getNarrowPhrase(Font font, float scaling, String... contents) {
        Phrase phrase = new Phrase();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
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
