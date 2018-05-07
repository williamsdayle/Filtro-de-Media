/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtrodemedia;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author willi
 */
public class Vizinhanca {

    BufferedImage imagem;
    double teta;
    int largura = 0;
    int altura = 0;
    double R;
    double G;
    double B;
    BufferedImage imagemFinal;

    public Vizinhanca() throws IOException {
        imagem = ImageIO.read(new File("image.jpg"));
        altura = imagem.getHeight();
        largura = imagem.getWidth();
        imagemFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

    }

    public int[] getValues(int i, int j) {
        Color c = new Color(imagem.getRGB(i, j));
        int[] values = new int[3];
        values[0] = c.getRed();
        values[1] = c.getGreen();
        values[2] = c.getBlue();

        return values;
    }

    public void calcVizinhanca() throws IOException {

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                try {
                    int red = getValues(i, j)[0];
                    int green = getValues(i, j)[1];
                    int blue = getValues(i, j)[2];
                    red += getValues(i + 1, j)[0];
                    green += getValues(i + 1, j)[1];
                    blue += getValues(i + 1, j)[2];
                    red += getValues(i, j + 1)[0];
                    green += getValues(i, j + 1)[1];
                    blue += getValues(i, j + 1)[2];
                    red += getValues(i - 1, j)[0];
                    green += getValues(i - 1, j)[1];
                    blue += getValues(i - 1, j)[2];
                    red += getValues(i, j - 1)[0];
                    green += getValues(i, j - 1)[1];
                    blue += getValues(i, j - 1)[2];
                    int valorVermelho = red / 5;
                    int valorVerde = green / 5;
                    int valorAzul = blue / 5;
                    Color temp = new Color(valorVermelho,valorVerde,valorAzul);
                    imagemFinal.setRGB(i, j, temp.getRGB());
                    

                } catch (ArrayIndexOutOfBoundsException ex) {

                }

            }

        }
        ImageIO.write(imagemFinal, "jpg", new File("ImagemFinal.jpg"));

    }

}
