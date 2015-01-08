package MiniCAD;

import java.awt.*;

/**
 * Created by desolate on 14/12/26.
 */
public class Rectangle extends MShape {
    public Rectangle (float width, Color color, Point begin, Point end) {
        this.width = width;
        this.color = color;
        beginPoint = begin;
        endPoint = end;
        status = 0;
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

        if (point.getX() >= minX - width / 2. && point.getX() <= maxX + width / 2. && point.getY() >= minY - width / 2. && point.getY() <= maxY + width / 2.) {
            if (point.getX() > minX + width / 2. && point.getX() < maxX - width / 2. && point.getY() > minY + width / 2. && point.getY() < maxY - width / 2.) {
                return -1;
            }else {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public void draw(Graphics x) {
        Graphics2D g = (Graphics2D)x;
        if (status == 0) {
            g.setStroke((new BasicStroke((float)width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        }else {
            g.setStroke((new BasicStroke((float)(width + 2.), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);

        g.drawRect(Math.min(beginPoint.x, endPoint.x), Math.min(beginPoint.y, endPoint.y), Math.abs(endPoint.x-beginPoint.x), Math.abs(endPoint.y-beginPoint.y));

        if (status != 0) {

            g.setColor(new Color(0,0,0));
            g.setStroke((new BasicStroke((float)width + 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
            g.drawLine(beginPoint.x, beginPoint.y, beginPoint.x, beginPoint.y);
            g.drawLine(endPoint.x, endPoint.y, endPoint.x, endPoint.y);
        }
    }
}
