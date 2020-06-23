import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CT extends Canvas {
    public static void main(String[] args) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Cooler Title");
        dialog.setSize(1280, 720);

        JPanel panel = new JPanel();

        Canvas canvas = new CT();
        canvas.setSize(7396, 2064);
        panel.add(canvas);

        JScrollPane scrollPane = new JScrollPane (panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        dialog.add(scrollPane);

        dialog.setVisible(true);

    }

    public void paint(Graphics g) {
        int[][] m = new Reader().fillMatrix();

        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[r].length; c++) {
                if (m[r][c] == 1) {
                    g.setColor(Color.BLACK);
                    g.drawLine(c,r,c,r);
                }

            }
        }
    }


}

class Reader {
    int[][] matrix = new int[2064][7396];

    public int[][] fillMatrix() {
        try {
            int column = 0;
            int row = 0;

            File ctan = new File("./src/assets/CTAN.dat");
            Scanner scanner = new Scanner(ctan);

            while (scanner.hasNextInt()) {
                if (column == 7396) {
                    column = 0;
                    row++;
                }
                matrix[row][column] = scanner.nextInt();
                column++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return matrix;
    }
}
