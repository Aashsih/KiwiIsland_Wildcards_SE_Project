package nz.ac.aut.ense701.gameModel.occupants;

import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.Occupants;
import nz.ac.aut.ense701.gameModel.Tools;

/**
 * This class represents a tool that can be found on the island
 * and gives the player any sort of advantage. 
 * 
 * @author AS
 * @version July 2011
 */

public class Tool extends Item 
{
    private boolean broken;
    
    /**
     * Construct a tool with known attributes.
     * @param pos the position of the tool
     * @param name the name of the tool
     * @param description a longer description of the tool
     * @param weight the weight of the tool
     * @param size the size of the tool
     */
    public Tool(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description, weight, size);
        this.broken = false;
    }
    
    /**
     * Break the tool
     */
    public void setBroken()
    {
        broken = true;
    }
    
    /**
     * Fix the tool
     */
    public void fix()
    {
        broken = false;
    }
    
    /**
     * Is tool broken
     * @return true if broken
     */
    public boolean isBroken()
    {
        return this.broken;
    }
    
    /**
    * Check if this tool is a predator trap
    * @return true if trap
     */
    public boolean isTrap()
    {
      String name = this.getName();
      return name.equalsIgnoreCase(Tools.TRAP.toString());
    }
 
    /**
    * Check if this tool is a screwdriver
    * @return true if screwdriver
     */    
    public boolean isScrewdriver() 
    {
       String name = this.getName();
      return name.equalsIgnoreCase(Tools.SCREW_DRIVER.toString()); 
    }
    
    
    @Override
    public String getStringRepresentation() 
    {
        return Occupants.TOOL.toString();
    }

}
