package MiniCAD;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by desolate on 14/12/24.
 */
public class Line extends MShape {
    Line2D l;
    public Line (float width, Color color, Point begin, Point end) {
        this.width = width;
        this.color = color;
        beginPoint = begin;
        endPoint = end;
        status = 0;
    }

    @Override
    public int pointInside(Point point) {
        double totalArea = Math.abs((beginPoint.x - endPoint.x) * (beginPoint.y - endPoint.y) / 2.0);
        double area1 = Math.abs((beginPoint.x - endPoint.x) * (beginPoint.y - point.y) / 2.0);
        double area2 = Math.abs((beginPoint.y - endPoint.y) * (point.x - endPoint.x) / 2.0);
        double last = Math.abs(totalArea - area1 - area2);
        double length = Math.sqrt((beginPoint.x - endPoint.x) * (beginPoint.x - endPoint.x) + (beginPoint.y - endPoint.y) * (beginPoint.y - endPoint.y));
        double height = last * 2 / length;

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

        if (height <= width / 2. && point.getX() >= minX - width / 2. && point.getX() <= maxX + width / 2. && point.getY() >= minY - width / 2. && point.getY() <= maxY + width / 2.) {
            return 0;
        }
        return -1;
    }

    @Override
    public void draw(Graphics x) {
        Graphics2D g = (Graphics2D)x;
        if (status == 0) {
            g.setStroke((new BasicStroke((float)width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        } else {
            g.setStroke((new BasicStroke((float)width + 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.drawLine(beginPoint.x, beginPoint.y, endPoint.x, endPoint.y);
        if (status != 0) {
            g.setColor(new Color(0,0,0));
            g.setStroke((new BasicStroke((float)width + 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
            g.drawLine(beginPoint.x, beginPoint.y, beginPoint.x, beginPoint.y);
            g.drawLine(endPoint.x, endPoint.y, endPoint.x, endPoint.y);
        }
//        l = new Line2D.Float(beginPoint, endPoint);
//        g.draw(l);

    }
}
