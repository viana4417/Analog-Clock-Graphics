import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
    Image clock = new ImageIcon("res/clock.png").getImage();
	
	LocalDateTime now = LocalDateTime.now();
    int hour = Integer.parseInt(now.format(DateTimeFormatter.ofPattern("hh")));
    int minute = now.getMinute();
    int second = now.getSecond();
    
    double angle_hours, angle_minutes, angle_seconds;
    final double CHANGE_SECONDS = 0.107;
    final double CHANGE_MINUTES = CHANGE_SECONDS;
    final double CHANGE_HOURS = CHANGE_SECONDS * 5;
    final int RADIUS_HOURS = 60;
    final int RADIUS_MINUTES = 100;
    final int RADIUS_SECONDS = 140;
    
    Font font = new Font("Agency FB", Font.PLAIN, 100);
    
    Panel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

    public void paint(Graphics g) {
  
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(50,50,50));
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.drawImage(clock, 0, Constants.CLOCK_Y, Constants.WINDOW_WIDTH, Constants.WINDOW_WIDTH, null);
     
        now = LocalDateTime.now();
        hour = Integer.parseInt(now.format(DateTimeFormatter.ofPattern("hh")));
        minute = now.getMinute();
        second = now.getSecond();
        
        angle_hours = -1.57 + (hour * CHANGE_HOURS);
        angle_minutes = -1.57 + (minute * CHANGE_MINUTES);
        angle_seconds = -1.57 + (second * CHANGE_SECONDS);
        
        drawPointers(g2, RADIUS_SECONDS, 2, new Color(255, 0, 0), angle_seconds);
        drawPointers(g2, RADIUS_MINUTES, 4, new Color(255, 255, 255), angle_minutes);
        drawPointers(g2, RADIUS_HOURS, 4, new Color(255, 255, 255), angle_hours);
        
        FontMetrics metric = g2.getFontMetrics(font);
        
        String time = (now.getHour() + ":" + minute + ":" + String.format("%02d", second));
        
        g2.setFont(font);
        g2.drawString(time, Constants.CENTER_X - metric.stringWidth(time) / 2, 150);

        try {
        	
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        repaint();
    }
    
    public void drawPointers(Graphics2D g2, int radius, int stroke, Color color, double angle) {
    	
    	int x = Constants.CENTER_X + (int) (radius * Math.cos(angle));
        int y = Constants.CENTER_Y + (int) (radius * Math.sin(angle));
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawLine(Constants.CENTER_X, Constants.CENTER_Y, x, y);
    	
    }
}