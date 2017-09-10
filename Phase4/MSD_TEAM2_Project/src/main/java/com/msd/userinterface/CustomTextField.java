package com.msd.userinterface;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * The Class CustomTextField to create a text field for the searchUI with
 * customized font and color
 */

public class CustomTextField extends JTextField {
	 
    /** The original font. */
    private Font originalFont;
    
    /** The original foreground. */
    private Color originalForeground;
    
    /** Grey by default*. */
    private Color placeholderForeground = new Color(160, 160, 160);
    
    /** The text written in. */
    private boolean textWrittenIn;
 
    /**
     *  Constructor
     * @param columns the columns
     */
    public CustomTextField(int columns) {
        super(columns);
    }
 
    /* (non-Javadoc)
     * @see javax.swing.JTextField#setFont(java.awt.Font)
     */
    @Override
    public void setFont(Font f) {
        super.setFont(f);
        if (!isTextWrittenIn()) {
            originalFont = f;
        }
    }
 
    /* (non-Javadoc)
     * @see javax.swing.JComponent#setForeground(java.awt.Color)
     */
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (!isTextWrittenIn()) {
            originalForeground = fg;
        }
    }
 
    /**
     * Gets the placeholder foreground.
     *
     * @return the placeholder foreground
     */
    public Color getPlaceholderForeground() {
        return placeholderForeground;
    }
 
    /**
     * Sets the placeholder foreground.
     *
     * @param placeholderForeground the new placeholder foreground
     */
    public void setPlaceholderForeground(Color placeholderForeground) {
        this.placeholderForeground = placeholderForeground;
    }
 
    /**
     * Checks if is text written in.
     *
     * @return true, if is text written in
     */
    public boolean isTextWrittenIn() {
        return textWrittenIn;
    }
 
    /**
     * Sets the text written in.
     *
     * @param textWrittenIn the new text written in
     */
    public void setTextWrittenIn(boolean textWrittenIn) {
        this.textWrittenIn = textWrittenIn;
    }
 
    /**
     * Sets the placeholder.
     *
     * @param text the new placeholder
     */
    public void setPlaceholder(final String text) {
 
        this.customizeText(text);
 
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }
 
            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
 
            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
 
            public void warn() {
                if (getText().trim().length() != 0) {
                    setFont(originalFont);
                    setForeground(originalForeground);
                    setTextWrittenIn(true);
                    setForeground(new Color(0, 0, 0));
                }
             }
        });
 
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!isTextWrittenIn()) {
                    setText("");
                    setFont(new Font("Tahoma", Font.PLAIN, 24));
                }
             }
 
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().trim().length() == 0) {
                    customizeText(text);
                }
            }
 
        });
 
    }
 
    /**
     * Customize text.
     *
     * @param text the text
     */
    private void customizeText(String text) {
        setText(text);
        /**If you change font, family and size will follow
         * changes, while style will always be italic**/
        setFont(new Font(getFont().getFamily(), Font.PLAIN, getFont().getSize()));
        setForeground(getPlaceholderForeground());
        setTextWrittenIn(false);
    }
 
}
