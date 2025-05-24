package application;

import javax.swing.SwingUtilities;

public class MouseHandler implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
    private int mouseX;
    private int mouseY;

    boolean attack = false;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // Handle mouse click events here
        if (SwingUtilities.isLeftMouseButton(e)) {
            attack = true;
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // Handle mouse press events here
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // Handle mouse release events here
        if (SwingUtilities.isLeftMouseButton(e)) {
            attack = false;
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // Handle mouse enter events here
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // Handle mouse exit events here
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // Handle mouse drag events here
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // Handle mouse move events here
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    public int getMouseX() {
        return mouseX;
    }
    
}
