/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author aashi
 */
public class ScoreBoardTest extends junit.framework.TestCase{
    private static final String ENCODING = "UTF-8";
    
    private StringBuilder playerScoreStringBuffer;

    /**
     * Default constructor for the class QuizDataTest
     */
    public ScoreBoardTest() {
        //Default constructor for the test class
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp() throws IllegalAccessException
    {
        try {
            playerScoreStringBuffer = new StringBuilder();
            playerScoreStringBuffer.append(IOUtils.toString(ScoreBoard.class.getResourceAsStream(TextFilePathConstants.PLAYER_SCORE), ENCODING));
            //Set Data to null to reset variables and make them read from the file again
            Field scoreBoardField = ScoreBoard.class.getDeclaredField("scoreList");
            scoreBoardField.setAccessible(true);
            scoreBoardField.set(null, null);
        } catch (IOException | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        try { 
            FileUtils.writeStringToFile(new File(ScoreBoard.class.getResource(TextFilePathConstants.PLAYER_SCORE).getFile()), playerScoreStringBuffer.toString() , ENCODING);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetScoreBoardFromFileReturnsListOfScore()
    {
        try 
        {
            String testData = getMockPlayerScoreData();
            FileUtils.writeStringToFile(new File(ScoreBoard.class.getResource(TextFilePathConstants.PLAYER_SCORE).getFile()), testData , ENCODING);
            List<Score> scoreBoard = ScoreBoard.getScoreBoard();
            assertEquals("Test Player 1", scoreBoard.get(0).getPlayerName());
            assertEquals(150, scoreBoard.get(0).getScore());
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testWriteScoreToFile(){
        try {
            List<Score> scoreBoard = ScoreBoard.getScoreBoard();
            int currentScoreListSize = 0;
            if(scoreBoard != null){
                currentScoreListSize = scoreBoard.size();   
            }
            Score newScore = new Score("Test Player name", 180);
            ScoreBoard.writeScoreToFile(newScore);
            scoreBoard = ScoreBoard.getScoreBoard();
            assertEquals(++currentScoreListSize, scoreBoard.size());
            assertEquals("Test Player name", scoreBoard.get(scoreBoard.size()-1).getPlayerName());
            assertEquals(180, scoreBoard.get(scoreBoard.size()-1).getScore());
        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            Logger.getLogger(ScoreBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getMockPlayerScoreData(){
        return "[\n" +
"{\n" +
"	\"playerName\":\"Test Player 1\",\n" +
"	\"playerScore\":150\n" +
"}\n" +
"]";
    }
}
