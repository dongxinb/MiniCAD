package MiniCAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by desolate on 14/12/23.
 */

public class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener {
    ShapeMode currentShapeMode;
    Color currentColor;
    MShape currentShape;
    MShape mouseShape;
    OperationMode operationMode;
    public Image image;

    Point beginPoint;

    public ArrayList<MShape> shapes;

    public CanvasPanel() {
        setBackground(new Color(255, 255, 255));

        addMouseListener(this);
        addMouseMotionListener(this);

        currentColor = new Color(30,144,255);

        shapes = new ArrayList<MShape>();

    }

    void changeColor(Color color) {

        if (operationMode == OperationMode.Select) {
            for (MShape i: shapes) {
                if (i.status == 2) {
                    i.color = color;
                }
            }
            repaint();
        }

    }

    MShape getShapeOnMouse(Point point) {
        for (MShape i: shapes) {
            if (i.pointInside(point) >= 0) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0,0,image.getWidth(this), image.getHeight(this), this);
        }
        for (MShape i: shapes) {
            i.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mouseShape != null) {
            if (mouseShape.status == 1) {
                mouseShape.status = 2;
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentShape = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        beginPoint = e.getPoint();
        MShape tt = getShapeOnMouse(e.getPoint());
        if (currentShape instanceof Text && currentShape != tt) {
            ((Text) currentShape).textArea.setOpaque(false);
            ((Text) currentShape).textArea.setEnabled(false);
        }
        if (tt == null) {
            operationMode = OperationMode.Draw;
        }else {
            int m = tt.pointInside(e.getPoint());
            if (m == 0)
                operationMode = OperationMode.Select;
            else if (m == 1)
                operationMode = OperationMode.ChangeSizeBegin;
            else if (m == 2)
                operationMode = OperationMode.ChangeSizeEnd;
        }

        if (operationMode == OperationMode.Draw) {
            int x = e.getX();
            int y = e.getY();
            if (currentShapeMode == ShapeMode.line) {
                Line line = new Line(3, currentColor, e.getPoint(), e.getPoint());
                currentShape = line;
                shapes.add(line);

                System.out.println(currentShapeMode + " add success.");
            }else if (currentShapeMode == ShapeMode.rectangle) {
                Rectangle rectangle = new Rectangle(3, currentColor, e.getPoint(), e.getPoint());
                currentShape = rectangle;
                shapes.add(rectangle);

                System.out.println(currentShapeMode + " add success.");
            }else if (currentShapeMode == ShapeMode.Cricle) {
                Circle circle = new Circle(3, currentColor, e.getPoint(), e.getPoint());
                currentShape = circle;
                shapes.add(circle);

                System.out.println(currentShapeMode + " add success.");
            }else if (currentShapeMode == ShapeMode.text) {
                Text text = new Text(currentColor, e.getPoint(), new Point(e.getX() + 1, e.getY() + 1));
                currentShape = text;
                shapes.add(text);
                add(text.textArea);
                System.out.println(currentShapeMode + " add success.");
            }
            if (mouseShape != null && mouseShape.status == 2) {
                MShape tshape = getShapeOnMouse(e.getPoint());
                if (tshape != mouseShape) {
                    mouseShape.status = 0;
                    mouseShape = tshape;
                }
            }
        }
    }

    //mouse motion
    @Override
    public void mouseDragged(MouseEvent e) {
        if (operationMode == OperationMode.Draw) {
            currentShape.endPoint = e.getPoint();
        }else if (operationMode == OperationMode.Select) {
//            int m = mouseShape.pointInside(e.getPoint());
                mouseShape.moveFromPointToPoint(beginPoint, e.getPoint());
                beginPoint = e.getPoint();
//            }else if (m == 1) {
//                mouseShape.moveBeginPointToPoint(beginPoint, e.getPoint());
//                beginPoint = e.getPoint();
//            }else if (m == 2) {
//                mouseShape.moveEndPointToPoint(beginPoint, e.getPoint());
//                beginPoint = e.getPoint();
//            }

        }else if (operationMode == OperationMode.ChangeSizeBegin) {
            mouseShape.beginPoint = e.getPoint();
        }else if (operationMode == OperationMode.ChangeSizeEnd) {
            mouseShape.endPoint = e.getPoint();
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MShape tShape = getShapeOnMouse(e.getPoint());
        if (tShape != mouseShape) {
            if (mouseShape != null) {
                if (mouseShape.status != 2) {
                    mouseShape.status = 0;
                    if (tShape != null) {
                        tShape.status  = 1;
                    }
                    mouseShape = tShape;
                }
            }else {
                if (tShape != null) {
                    tShape.status = 1;
                }
                mouseShape = tShape;
            }

        }
        repaint();
    }
}
