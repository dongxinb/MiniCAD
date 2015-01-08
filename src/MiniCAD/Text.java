package MiniCAD;

import javax.swing.*;
import java.awt.*;

/**
 * Created by desolate on 14/12/26.
 */
public class Text extends MShape {
    JTextArea textArea;

    public Text (Color color, Point begin, Point end) {
        this.color = color;
        beginPoint = begin;
        endPoint = end;
        status = 0;

        textArea = new JTextArea("text");
        textArea.setLocation(begin.x, begin.y);
        textArea.setSize(1, 1);

        textArea.setVisible(true);
        textArea.setEnabled(true);
        textArea.setBackground(new Color(1.f, 1.f, 1.f, 0.f));
        textArea.setOpaque(false);
    }

    @Override
    public int pointInside(Point point) {

        double minX = Math.min(beginPoint.getX(), endPoint.getX());
        double maxX = Math.max(beginPoint.getX(), endPoint.getX());
        double minY = Math.min(beginPoint.getY(), endPoint.getY());
        double maxY = Math.max(beginPoint.getY(), endPoint.getY());

        if ((beginPoint.x - point.x) * (beginPoint.x - point.x) + (beginPoint.y - point.y) * (beginPoint.y - point.y) <= ((int)width + 6) * ((int)width + 6) / 4) {
            return 1;
        }

        if ((endPoint.x - point.x) * (endPoint.x - point.x) + (endPoint.y - point.y) * (endPoint.y - point.y) <= ((int)width + 6) * ((int)width + 6) / 4) {
            return 2;
        }

        if (point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY) {
            return 0;
        }
        return -1;
    }

    @Override
    public void draw(Graphics x) {
        double minX = Math.min(beginPoint.getX(), endPoint.getX());
        double maxX = Math.max(beginPoint.getX(), endPoint.getX());
        double minY = Math.min(beginPoint.getY(), endPoint.getY());
        double maxY = Math.max(beginPoint.getY(), endPoint.getY());

//        textArea.setLocation((int)minX, (int)minY);
        textArea.setLocation(beginPoint);
        textArea.setSize(endPoint.x - beginPoint.x, endPoint.y - beginPoint.y);
//        textArea.setSize((int)(maxX - minX), (int)(maxY - minY));
        textArea.setForeground(color);
        if (status != 0) {

//            g.setColor(new Color(0,0,0));
//            g.setStroke((new BasicStroke((float)width + 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
//            g.drawLine(beginPoint.x, beginPoint.y, beginPoint.x, beginPoint.y);
//            g.drawLine(endPoint.x, endPoint.y, endPoint.x, endPoint.y);
        }
    }
}
