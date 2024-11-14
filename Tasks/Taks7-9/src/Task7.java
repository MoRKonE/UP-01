import javax.swing.*;
import java.awt.*;

public class Task7 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.fillRect(145, 55, 20, 10);
        int[] xPoints = {120, 155, 180};
        int[] yPoints = {100, 60, 100};
        g2d.drawPolygon(xPoints, yPoints, 3);

        g2d.drawLine(120,100,90,200);
        g2d.drawLine(180,100,200,200);
        g2d.drawLine(90,200,200,200);

        g2d.fillRect(120,100,60,15);
        int[] xP1 = {120,115,180};
        int[] yP1 = {100,115,115};
        int[] xP2 = {120,180,185};
        int[] yP2 = {115,100,115};
        g2d.fillPolygon(xP1, yP1, 3);
        g2d.fillPolygon(xP2,yP2,3);

        g2d.fillRect(95,185,102,15);
        int[] xD1 = {95,90,100};
        int[] yD1 = {185,200,200};
        int[] xD2 = {197,200,190};
        int[] yD2 = {185,200,200};
        g2d.fillPolygon(xD1,yD1,3);
        g2d.fillPolygon(xD2,yD2,3);

        g2d.drawLine(190,140,240,90);
        g2d.drawLine(193,165,260,90);
        g2d.drawLine(240,90,260,90);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Figure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Task7());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task7::createAndShowGUI);
    }
}
