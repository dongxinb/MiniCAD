package MiniCAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by desolate on 14/12/23.
 */
public class OperationPanel extends JPanel {
    public CanvasPanel canvasPanel;

    JButton circle;
    JButton rectangle;
    JButton line;
    JButton text;
    JButton color;

    public OperationPanel(final CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

        circle = new JButton("圆");
        rectangle = new JButton("矩形");
        line = new JButton("直线");
        text = new JButton("文本");
        color = new JButton("颜色");

        this.add(circle);
        this.add(rectangle);
        this.add(line);
        this.add(text);
        this.add(color);
        this.setLayout(new GridLayout(20, 2));

        setSelected(circle);

        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelected(circle);

            }
        });

        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelected(rectangle);

            }
        });

        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelected(line);

            }
        });

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelected(text);

            }
        });

        color.setForeground(new Color(30,144,255));
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color choose = new JColorChooser().showDialog(color, "颜色选择", Color.black);
                canvasPanel.currentColor = choose;
                canvasPanel.changeColor(choose);
                if(choose != null)
                    color.setForeground(choose);
            }
        });

    }

    private void setSelected(JButton btn) {
        if (btn == circle) {
            circle.setSelected(true);
            canvasPanel.currentShapeMode = ShapeMode.Cricle;
        }else {
            circle.setSelected(false);
        }
        if (btn == rectangle) {
            rectangle.setSelected(true);
            canvasPanel.currentShapeMode = ShapeMode.rectangle;
        }else {
            rectangle.setSelected(false);
        }
        if (btn == line) {
            line.setSelected(true);
            canvasPanel.currentShapeMode = ShapeMode.line;
        }else {
            line.setSelected(false);
        }
        if (btn == text) {
            text.setSelected(true);
            canvasPanel.currentShapeMode = ShapeMode.text;
        }else {
            text.setSelected(false);
        }

    }

}
