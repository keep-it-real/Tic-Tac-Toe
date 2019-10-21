package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TicTacToe extends JFrame
{
    private JPanel boardPanel = new JPanel();
    private JPanel scorePanel = new JPanel();
    private JPanel rePanel = new JPanel();
    private OXButton[] button = new OXButton[9];
    private ScoreLabel[] label = new ScoreLabel[4];
    private REButton[] button2 = new REButton[2];
    private int oCounter = 0;
    private int xCounter = 0;
    private String turn = "O";
            
    public TicTacToe()
    {
        initComponents(); 
    }
        
    public void initComponents()
    {    
        this.setTitle("Tic Tac Toe");
        this.setBounds(300, 100, 1400, 800);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scorePanel, rePanel); 
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, split);
        mainSplit.setResizeWeight(0.75);
        split.setResizeWeight(0.68);
        this.getContentPane().add(mainSplit);
        
        boardPanel.setLayout(new GridLayout(3, 3));
        scorePanel.setLayout(new GridLayout(2, 2));
        rePanel.setLayout(new GridLayout(1, 2));
        
        for(int i = 0; i < button.length; i++)
        {
            button[i] = new OXButton();
            boardPanel.add(button[i]);
        }
               
        label[0] = new ScoreLabel("Player O:");
        label[1] = new ScoreLabel("0");
        label[2] = new ScoreLabel("Player X:");
        label[3] = new ScoreLabel("0");
       
        for(int i = 0; i < label.length; i++)
        {
            scorePanel.add(label[i]);
            label[i].set();  
        }
        
        button2[0] = new REButton("Reset");
        button2[0].addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
              reset();
            }
        });
        button2[1] = new REButton("Exit");
        button2[1].addActionListener(new ActionListener() 
          {
              @Override
              public void actionPerformed(ActionEvent ae) 
              {
                 if(JOptionPane.showConfirmDialog(null, "Do you wanna exit?", 
                       "Tic Tac Toe", JOptionPane.YES_NO_OPTION) ==
                       JOptionPane.YES_NO_OPTION)
               {
                   System.exit(0);
               }
              }
          });
        
        for(int i = 0; i < button2.length; i++)
        {
            rePanel.add(button2[i]);
            button2[i].set();
        }   
    }
    
    public void changePlayer()
    {
        if(turn.equalsIgnoreCase("O"))
            turn = "X";
        else
            turn = "O";
    }
    
    public void gameScore()
    {
        label[1].setText(String.valueOf(oCounter));
        label[3].setText(String.valueOf(xCounter));
    }
    
    public void reset()
    {  
        for(int j = 0; j < 9; j++)
        button[j].setText(null); 
        for(int i = 0; i < 9; i++)
        button[i].setEnabled(true);
    }
    
    public void winGame()
    {
        String b1 = button[0].getText();
        String b2 = button[1].getText();
        String b3 = button[2].getText();
        String b4 = button[3].getText();
        String b5 = button[4].getText();
        String b6 = button[5].getText();
        String b7 = button[6].getText();
        String b8 = button[7].getText();
        String b9 = button[8].getText();
        
        if(b1 == ("O") && b2 == ("O") && b3 == ("O") || b1 == ("O") && b4 == ("O") && b7 == ("O") ||
            b1 == ("O") && b5 == ("O") && b9 == ("O") || b2 == ("O") && b5 == ("O") && b8 == ("O") ||
            b3 == ("O") && b6 == ("O") && b9 == ("O") || b3 == ("O") && b5 == ("O") && b7 == ("O") ||
            b4 == ("O") && b5 == ("O") && b6 == ("O") || b7 == ("O") && b8 == ("O") && b9 == ("O"))
            {
                oCounter++;
                JOptionPane.showMessageDialog(null, "Player O wins", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
                gameScore();
                reset(); 
            }
        
        if(b1 == ("X") && b2 == ("X") && b3 == ("X") || b1 == ("X") && b4 == ("X") && b7 == ("X") ||
               b1 == ("X") && b5 == ("X") && b9 == ("X") || b2 == ("X") && b5 == ("X") && b8 == ("X") ||
               b3 == ("X") && b6 == ("X") && b9 == ("X") || b3 == ("X") && b5 == ("X") && b7 == ("X") ||
               b4 == ("X") && b5 == ("X") && b6 == ("X") || b7 == ("X") && b8 == ("X") && b9 == ("X"))
            {
                xCounter++;
                JOptionPane.showMessageDialog(null, "Player X wins", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
                gameScore();
                reset();
            }
    }
        
    private class OXButton extends JButton
    {
        public OXButton()
        {
            setFont(new Font("Serif", Font.BOLD, 100));
            addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                   setText(turn);
                   setEnabled(false);
                   changePlayer();
                   winGame();
                }
            });
        }       
    }     
    
    private class ScoreLabel extends JLabel
    {
        private String name;
        
        public ScoreLabel(String name)
        {
          this.name = name; 
        }
        public void set()
        {
            setText(name);
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Serif", Font.BOLD, 40));
            setBorder(new LineBorder(new Color(0, 0, 0), 1));
        } 
    }
    
   private class REButton extends JButton
    {
        private String name;
        
        public REButton(String name)
        {
          this.name = name; 
        }
        
        public void set()
        {
            setText(name);
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Serif", Font.BOLD, 40));
            setBorder(new LineBorder(new Color(0, 0, 0), 1));
        } 
   }
   
    public static void main(String[] args) 
    {
        new TicTacToe().setVisible(true);
    }
}
