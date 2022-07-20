import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinha {
    public void cria() throws Exception{
        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("aluara_stickrs/entrada/filme.jpg"));
        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/MostPopularTVs_2.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura,novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0, 0,null);

        //configura a fonte 
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(font);

        //escrever uma frase na nova imagem
        graphics.drawString("segura o tchan", 100, novaAltura  -80);
        graphics.setColor(Color.GRAY);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("aluara_stickrs/saida/filme_novo.png"));
    }

    public static void main(String[] args) {
       var gerar =  new GeradoraDeFigurinha();
       try {
        gerar.cria();
    } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Arquivo Não encontrado...");
    }
    }
}
