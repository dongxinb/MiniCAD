import MiniCAD.CanvasPanel;
import MiniCAD.OperationPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
//        OpFrame frame = new OpFrame("MiniCAD");
        JFrame frame = new JFrame("MiniCAD");

        final CanvasPanel canvasPanel = new CanvasPanel();
        OperationPanel operationPanel = new OperationPanel(canvasPanel);

        frame.add(operationPanel, BorderLayout.WEST);
        frame.add(canvasPanel, BorderLayout.CENTER);

        JMenuBar menu = new JMenuBar();
        JMenu menuItem = new JMenu("文件");
        JMenuItem item1 = new JMenuItem("读取文件");
        JMenuItem item2 = new JMenuItem("保存文件");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser choose=new JFileChooser();
                int n = choose.showOpenDialog(null);
                if (n == JFileChooser.APPROVE_OPTION) {

                    Image image;
                    try {
                        image = ImageIO.read(new File(choose.getSelectedFile().toString()));

                        Graphics g = canvasPanel.getGraphics();


                        g.drawImage(image, 0, 0, image.getWidth(canvasPanel),image.getHeight(canvasPanel), canvasPanel);
                        JOptionPane.showMessageDialog(canvasPanel, "导入成功");
                        canvasPanel.image = image;
                        canvasPanel.shapes.clear();

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(canvasPanel, "导入失败");

                    }
                }
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser save = new JFileChooser();
                int n = save.showSaveDialog(null);
                if (n == JFileChooser.APPROVE_OPTION) {
                    BufferedImage image = (BufferedImage)canvasPanel.createImage(canvasPanel.getWidth(), canvasPanel.getHeight());
                    canvasPanel.paint(image.getGraphics());
                    try {
                        ImageIO.write(image, "jpeg",  new java.io.File(save.getSelectedFile().toString()));
                        JOptionPane.showMessageDialog(canvasPanel, "导出文件成功");

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(canvasPanel, "导出文件失败");

                    }
                }
            }
        });

        menu.setVisible(true);
        menuItem.add(item1);
        menuItem.add(item2);
        menu.add(menuItem);
        frame.setJMenuBar(menu);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}