package MiniCAD;

import java.awt.*;

/**
 * Created by desolate on 14/12/24.
 */
public class MShape {
    Point beginPoint;
    Point endPoint;
    double width;
    Color color;

    int status;

    public int pointInside(Point point) {
        return -1;
    }


    public void draw(Graphics x) {

    }

    public void moveFromPointToPoint(Point start, Point to) {
        beginPoint.x += to.x - start.x;
        beginPoint.y += to.y - start.y;
        endPoint.x += to.x - start.x;
        endPoint.y += to.y - start.y;
    }

    public  void moveBeginPointToPoint(Point start, Point to) {
        beginPoint.x += to.x - start.x;
        beginPoint.y += to.y - start.y;
    }

    public  void moveEndPointToPoint(Point start, Point to) {
        endPoint.x += to.x - start.x;
        endPoint.y += to.y - start.y;
    }
}
