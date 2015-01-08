package MiniCAD;

import java.awt.*;

/**
 * Created by desolate on 14/12/26.
 */
public class Circle extends MShape {
    public Circle (float width, Color color, Point begin, Point end) {
        this.width = width;
        this.color = color;
        beginPoint = begin;
        endPoint = end;
        status = 0;
    }

    @Override
    public int pointInside(Point point) {

        if (status == 2) {
            if ((beginPoint.x - point.x) * (beginPoint.x - point.x) + (beginPoint.y - point.y) * (beginPoint.y - point.y) <= ((int)width + 6) * ((int)width + 6) / 4) {
                return 1;
            }
            if ((endPoint.x - point.x) * (endPoint.x - point.x) + (endPoint.y - point.y) * (endPoint.y - point.y) <= ((int)width + 6) * ((int)width + 6) / 4) {
                return 2;
            }
        }

        double x = (beginPoint.getX() + endPoint.getX()) / 2.;
        double y = (beginPoint.getY() + endPoint.getY()) / 2.;

        double minX = Math.min(beginPoint.getX(), endPoint.getX());
        double minY = Math.min(beginPoint.getY(), endPoint.getY());

        double a = x - minX;
        double b = y - minY;

        double ox = point.getX() - x;
        double oy = point.getY() - y;

        double f1 = ox * ox / ((a + width / 2.) * (a + width / 2.)) + oy * oy / ((b + width / 2.) * (b + width / 2.));
        double f2 = ox * ox / ((a - width / 2.) * (a - width / 2.)) + oy * oy / ((b - width / 2.) * (b - width / 2.));
        if (f1 <= 1 && f2 >=1) {
            return 0;
        }
        return -1;
//        double r = Math.abs();
//        double rr = Math.sqrt((point.getX() - x) * (point.getX() - x) + (point.getY() - y) * (point.getY() - y));
//        if (Math.abs(r - rr) <= width / 2.) {
//            return 0;
//        }
    }

    @Override
    public void draw(Graphics x) {
        Graphics2D g = (Graphics2D)x;
        if (status == 0) {
            g.setStroke((new BasicStroke((float)width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        } else {
            g.setStroke((new BasicStroke((float)width + 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        double xx = beginPoint.getX() + endPoint.getX() / 2.;
        double yy = beginPoint.getY() + endPoint.getY() / 2.;
        double r = Math.sqrt((beginPoint.getX() - xx) * (beginPoint.getX() - xx) + (beginPoint.getY() - yy) * (beginPoint.getY() - yy));

        int rr = Math.min(Math.abs(beginPoint.x - endPoint.x), Math.abs(beginPoint.y - endPoint.y));
//        g.drawOval(Math.min(beginPoint.x, endPoint.x), Math.min(beginPoint.y, endPoint.y), rr, rr);
        g.drawOval(Math.min(beginPoint.x, endPoint.x), Math.min(beginPoint.y, endPoint.y), Math.abs(endPoint.x-beginPoint.x), Math.abs(endPoint.y-beginPoint.y));
//        g.drawOval((int)(xx - r), (int)(yy - r), (int)(r * 2.), (int)(r * 2.));
//        g.drawOval(beginPoint.x - Math.abs(endPoint.y - beginPoint.y), beginPoint.y - Math.abs(endPoint.y - beginPoint.y), Math.abs(endPoint.y - beginPoint.y) * 2, 2 * Math.abs(endPoint.y - beginPoint.y));
        if (status != 0) {

            g.setColor(new Color(0,0,0));

            g.setStroke(new BasicStroke(1.f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,
                    1.f,new float[]{15,10,},0f));
            g.drawRect(Math.min(beginPoint.x, endPoint.x), Math.min(beginPoint.y, endPoint.y), Math.abs(endPoint.x - beginPoint.x), Math.abs(endPoint.y - beginPoint.y));


            g.setStroke((new BasicStroke(6.f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
            g.drawLine(beginPoint.x, beginPoint.y, beginPoint.x, beginPoint.y);
            g.drawLine(endPoint.x, endPoint.y, endPoint.x, endPoint.y);
        }
//        l = new Line2D.Float(beginPoint, endPoint);
//        g.draw(l);

    }

}
