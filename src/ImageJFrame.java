import javax.swing.*;
import java.awt.*;
import java.nio.channels.NetworkChannel;
import java.util.*;
import javax.swing.Timer;

public class ImageJFrame
{
  ImageJFrame(ArrayList<Card> cpuH, ArrayList<Card> pH, String winner, String pHStr, String cpuHStr) 
  {
    JFrame f = new JFrame("Poker");
    f.setSize(450, 500);
    f.setLayout(null);

    
    int cardWidth = 90;
    int totalWidth = cardWidth * cpuH.size();
    int frameWidth = 450;
    int startX = (frameWidth - totalWidth) / 2;
    
    for(int i = 0;i < cpuH.size();i++){
      int index = i;
      new javax.swing.Timer(200 * i, e -> {
          JLabel j = new JLabel();
          j.setBounds(0 + (90 * index), 15, 90, 126);
          ImageIcon icon = new ImageIcon(cpuH.get(index).getImgStr());
          ImageIcon scaledIcon = resize(icon, j);
          j.setIcon(scaledIcon);
          f.add(j);
          f.revalidate();
          f.repaint();
      }).start();
    }
    for(int i = 0;i < pH.size();i++)
    {
        int index = i;
        new javax.swing.Timer(1000 + 200 * i, e -> {
            JLabel j = new JLabel();
            j.setBounds(0 + (90 * index), 260, 90, 126);
            ImageIcon icon = new ImageIcon(pH.get(index).getImgStr());
            ImageIcon scaledIcon = resize(icon, j);
            j.setIcon(scaledIcon);
            f.add(j);
            f.revalidate();
            f.repaint();
        }).start();
    }
      JLabel dealing = new JLabel("Dealing...");
      dealing.setFont(new Font("Arial", Font.BOLD, 18));
      dealing.setForeground(Color.BLACK);
      Dimension dealingSize = dealing.getPreferredSize();
      dealing.setBounds(225 - dealingSize.width / 2, 200,  dealingSize.width, dealingSize.height);
      f.add(dealing);
      
      int dealTime = 2000;
      Timer removeDealing = new javax.swing.Timer(dealTime + 250, e -> {
          f.remove(dealing);
          f.revalidate();
          f.repaint();
      });
      removeDealing.start();
      Timer reveal = new javax.swing.Timer (dealTime + 300, e -> {

          int centerX = 225;
          JLabel cpuLabel = new JLabel("Dealer: " + cpuHStr);
          cpuLabel.setFont(new Font("Arial", Font.BOLD, 18));
          cpuLabel.setForeground(Color.BLACK);
          Dimension size = cpuLabel.getPreferredSize();
          cpuLabel.setBounds(centerX - size.width / 2, 155, size.width, size.height);
          f.add(cpuLabel);
        
          JLabel label = new JLabel("Player: " + pHStr);
          label.setFont(new Font("Arial", Font.BOLD, 18));
          label.setForeground(Color.BLACK);
          size = label.getPreferredSize();
          centerX = 225; 
          label.setBounds(centerX - size.width / 2, 400, size.width, size.height);
          f.add(label);
        
          JLabel winnerLabel;
        
          if (winner.equals("Dealer"))
              winnerLabel = new JLabel("Dealer Wins!");
          else if (winner.equals("Player"))
              winnerLabel = new JLabel("Player Wins!");
          else
              winnerLabel = new JLabel("Tie!");
          winnerLabel.setFont(new Font("Arial", Font.BOLD, 18));
          size = winnerLabel.getPreferredSize();
          winnerLabel.setBounds(centerX - size.width / 2, 200,  size.width, size.height);
          winnerLabel.setForeground(Color.WHITE);
          
          
        
          f.add(winnerLabel);

          JPanel cpuGlow = new JPanel();
          cpuGlow.setBounds(startX - 5, 10, totalWidth + 10, 126 + 10);

          if (winner.equals("Dealer")) {
              cpuGlow.setBackground(new Color(255, 215, 0, 100));
              cpuGlow.setOpaque(true);
              cpuGlow.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
              f.add(cpuGlow);
          }

          System.out.println(winner);
          JPanel playerGlow = new JPanel();
          playerGlow.setBounds(startX - 5, 260 - 5, totalWidth + 10, 126 + 10);

          if (winner.equals("Player")) {
              playerGlow.setBackground(new Color(255, 215, 0, 100));
              playerGlow.setOpaque(true);
              playerGlow.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
              f.add(playerGlow);
          }
          
          cpuLabel.setHorizontalAlignment(SwingConstants.CENTER);
          label.setHorizontalAlignment(SwingConstants.CENTER);
          winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
          f.revalidate();
          f.repaint();
      });
      reveal.start();
      reveal.setRepeats(false);
      
    f.getContentPane().setBackground(Color.GREEN.darker());
    JButton playAgain = new JButton("Deal New Hand");
    playAgain.setBounds(150, 430, 150, 30);
    
    playAgain.addActionListener(e -> {
        f.dispose(); // close current window
        new Poker(); // start fresh game
    });
    
    f.add(playAgain);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }

  public ImageIcon resize(ImageIcon img, JLabel label)
  {
    Image rImg = img.getImage();
    Image imgScale = rImg.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(imgScale);
    return scaledIcon;
  }
}
