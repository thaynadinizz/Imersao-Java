import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String  nomeArquivo) throws IOException {

        // leitura da imagem

        //InputStream inputStream = 
        //                         new FileInputStream( new File( "entrada/filme-maior.jpg"));
        //InputStream inputStream =
        //                        new URL("https://i.pinimg.com/236x/60/5e/46/605e461ca634e868085d1a3d9d02b1ea.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        
        // criar nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem originial para novo imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,  0, 0, null);


        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("GF?", 70, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File( nomeArquivo));
    }

 }

