/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.occupants;

import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.enums.Occupants;

/**
 *
 * @author aashi
 */
public class Bait extends Item
{

    private boolean used;
    
    /**
     * Construct a bait with known attributes.
     * @param pos the position of the tool
     * @param name the name of the tool
     * @param description a longer description of the tool
     * @param weight the weight of the tool
     * @param size the size of the tool
     */
    public Bait(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description, weight, size);
        this.used = false;
    }
    
    /**
     * Set the bait to used 
     */
    public void setBroken()
    {
        used = true;
    }
    
    /**
     * Is bait used
     * @return true if used
     */
    public boolean isBroken()
    {
        return this.used;
    }
    
    @Override
    public String getStringRepresentation() {
        return Occupants.BAIT.toString();
    }
      
}
