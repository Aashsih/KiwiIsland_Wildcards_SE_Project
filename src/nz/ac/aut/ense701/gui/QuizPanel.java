/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import nz.ac.aut.ense701.gameQuiz.Question;
import nz.ac.aut.ense701.gameQuiz.Quiz;

/**
 *
 * @author aashi
 */
public class QuizPanel extends javax.swing.JPanel {
    
    private List<JRadioButton> optionList;
    private Question question;
    /**
     * Creates new form QuizPanel
     */
    public QuizPanel(KiwiCountUI parentFrame, Quiz quiz) {
        if(quiz == null || parentFrame == null){
            throw new IllegalArgumentException("The Arguments supplied cannot be null");
        }
        this.parentFrame = parentFrame;
        this.quiz = quiz;
        initComponents();
        this.txtPaneQuestion.setEditable(false);
        question = quiz.getNextQuestion();
        this.prepareQuizPanelForAQuestion(question);
    }
    
    /**
     * Prepares the QuizPanel by adding the question to the txtPaneQuestion
     * and creating radio-buttons for the different option dynamically
     * @param question 
     */
    private void prepareQuizPanelForAQuestion(Question question){
        if(question == null){
            //finish quiz and start new game
            this.parentFrame.disposeKiwiCountUIFrame();
        }
        else{
            txtPaneQuestion.setText(question.getQuestion());
            addRadioButtons(question.getOptions());
        }
    }
    
    /**
     * Dynamically adds JRadioButtons to the QuizPanel
     * @param options 
     */
    private void addRadioButtons(List<String> options){
        
        if(optionList != null && !optionList.isEmpty()){
            
            for(JRadioButton option : optionList){
                
                option.setVisible(false);
                this.remove(option);
            }
        }
        
        optionList = new ArrayList<JRadioButton>();
        java.awt.GridBagConstraints gridBagConstraints;
        radioButtonGroup = new ButtonGroup();
        int currentRowCount = ((GridBagLayout)(jPanel2.getLayout())).getLayoutDimensions().length;
        if(jPanel2.getLayout() instanceof GridBagLayout){
            for(String option : options){
                JRadioButton radioButton = new JRadioButton(option);
                radioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy =  ++currentRowCount;
                gridBagConstraints.ipadx = 20;
                gridBagConstraints.ipady = 20;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
                gridBagConstraints.weightx = 1.0;
                jPanel2.add(radioButton, gridBagConstraints);
                radioButtonGroup.add(radioButton);
                optionList.add(radioButton);
            }
        }
        this.revalidate();
        this.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPaneQuestion = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(540, 592));
        setLayout(new java.awt.BorderLayout(20, 0));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Question");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        jScrollPane1.setViewportView(txtPaneQuestion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Option");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jLabel2, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 15));

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        
        int selectedAnswer = getSelectedRadioButton();  
        displayResult(quiz.isAnswerCorrect(question, selectedAnswer), quiz.correctAnswer()-1);
        prepareQuizPanelForAQuestion(question = quiz.getNextQuestion());
    }//GEN-LAST:event_btnNextActionPerformed

    /**
     * 
     * @param result
     * @param selected 
     */
    private void displayResult(boolean result, int selected){
        
        if(result){
            JOptionPane.showMessageDialog(parentFrame, "Correct Answer!", "Result", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            String answer = question.getOptions().get(selected);
            JOptionPane.showMessageDialog(parentFrame, "Correct Answer is : \n"+answer, "Result", JOptionPane.PLAIN_MESSAGE);
        }
    }
    private int getSelectedRadioButton(){
        
        for(int i = 0 ; i < optionList.size(); i++){
            
            if (optionList.get(i).isSelected()){
                
                return i+1;
            } 
        }
        return -1;
    }
    private ButtonGroup radioButtonGroup;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtPaneQuestion;
    // End of variables declaration//GEN-END:variables
    private Quiz quiz;
    private KiwiCountUI parentFrame;
}
