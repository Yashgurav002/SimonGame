package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class SimonGame extends JFrame implements ActionListener {
    private List<Integer> sequence;
    private List<JButton> buttons;
    private int currentStep;
    private boolean playerTurn;
    private Timer timer;
    private int currentFlash;

    public SimonGame(){
        setTitle("Simon Game by BuildWithYash");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));

        sequence= new ArrayList<>();
        buttons=new ArrayList<>();
        currentStep=0;
        playerTurn=false;

        for(int i=0;i<4;i++){
            JButton button= new SimonButton(i);
            button.addActionListener(this);
            buttons.add(button);
            add(button);
        }

        newRound();
        setVisible(true);
    }

    private void newRound() {
        Random random = new Random();
        sequence.add(random.nextInt(4)); // Add a new random step to the sequence
        currentStep = 0; // Reset the player's progress for the new round
        playerTurn = false; // Disable player input while the sequence is playing
        playSequence(); // Play the new sequence
    }

    private void playSequence(){
        timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentFlash<sequence.size()){
                    buttons.get(sequence.get(currentFlash)).setBackground((Color.WHITE));
                    Timer pause= new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttons.get(sequence.get(currentFlash)).setBackground(getButtonColor(sequence.get(currentFlash)));
                            currentFlash++;
                        }
                    });
                    pause.setRepeats(false);
                    pause.start();
                }else{
                    timer.stop();
                    playerTurn=true;
                }
            }
        });
        currentFlash=0;
        timer.setRepeats(true);
        timer.start();

    }

    private Color getButtonColor(int index){
        switch (index){
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            default : return Color.BLACK;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!playerTurn) {
            return; // Ignore clicks if it's not the player's turn
        }

        SimonButton button = (SimonButton) e.getSource();

        // Check if the current button matches the current step in the sequence
        if (button.getIndex() == sequence.get(currentStep)) {
            currentStep++; // Player successfully matched this step

            // If the player has matched the entire sequence
            if (currentStep == sequence.size()) {
                playerTurn = false; // Player's turn is over
                JOptionPane.showMessageDialog(this, "Good job! Starting round :"+sequence.size());
                newRound(); // Start the next round
            }
        } else {
            // Player made an incorrect move
            JOptionPane.showMessageDialog(this, "Game Over! Your Score: " + (sequence.size() - 1));
            sequence.clear(); // Clear the sequence to reset the game
            newRound(); // Start a new game
        }
    }

}
