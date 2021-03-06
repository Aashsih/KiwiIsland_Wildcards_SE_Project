/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import nz.ac.aut.ense701.gamemodel.handlers.KiwiHandler;
import nz.ac.aut.ense701.gamemodel.occupants.Fauna;
import nz.ac.aut.ense701.gamemodel.occupants.Hazard;
import nz.ac.aut.ense701.gamemodel.occupants.Kiwi;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class KiwiHandlerTest extends junit.framework.TestCase{
    private static final String TEST_KIWI = "Test Kiwi";
    private static final String TEST_FAUNA = "Test Fauna";
    private static final String KIWIS_CANNOT_MOVE = "The kiwis around you could not move.";
    
    private KiwiHandler kiwiHandler;
    private Island island;
    
    /**
     * Default constructor for test class KiwiHandlerTest
     */
    public KiwiHandlerTest()
    {
        //Default constructor for the test class   
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        island = new Island(10, 10);
        kiwiHandler = new KiwiHandler(island);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        island = null;
        kiwiHandler = null;
    }
    
    @Test
    public void testAtrractKiwisIllegalArgumentException()
    {
        try
        {
            kiwiHandler.attractKiwis(null);
            fail("IllegalArgumentException did not occur");
        }
        catch(Exception e)
        {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
    }
    
    @Test
    public void testAtrractKiwisNoKiwisOnIsland()
    {
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("No nearby kiwis.", result);
    }
    
    @Test
    public void testAtrractKiwisOutsideAttractRadius()
    {
        Position position = new Position(island, 2, 2); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("No nearby kiwis.", result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToHazards()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Hazard(position,"Hazard", "Test Hazard", 1));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Hazard(position,"Hazard", "Test Hazard", 1));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals(KIWIS_CANNOT_MOVE, result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToNeighbouringKiwis()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        position = new Position(island, 5, 5); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals(KIWIS_CANNOT_MOVE, result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToGridSqaureCapacity()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", TEST_KIWI));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna1", TEST_FAUNA));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna2", TEST_FAUNA));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna3", TEST_FAUNA));
        
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna1", TEST_FAUNA));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna2", TEST_FAUNA));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna3", "Test Fauna"));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals(KIWIS_CANNOT_MOVE, result);
    }
}
