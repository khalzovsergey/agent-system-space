/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import jade.core.behaviours.Behaviour;
import java.util.Map;
import space.Scheduler;

/**
 *
 * @author Sergey
 */
public class ResourceBehaviour extends Behaviour
{
    //private boolean finished = false;
    private Map<String, Object> planet;
    private int delay = 900;
    private Scheduler scheduler;

    public ResourceBehaviour(Map<String, Object> planet)
    {
        this.planet = planet;
        scheduler = new Scheduler();
    }

    public void action()
    {
        if (scheduler.mayExecute())
        {
            scheduler.addDelay(delay);
        }
        scheduler.block(this);
    }

    public boolean done()
    {
        return false;
    }
}
