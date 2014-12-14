/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import jade.core.behaviours.Behaviour;
import java.util.Map;
import space.common.Scheduler;
import space.common.Variable;

/**
 *
 * @author Sergey
 */
public class ResourcesBehaviour extends Behaviour
{
    private Map<String, Object> planet;
    private Map<String, Object> resources;
    private int delay = 1000;
    private Scheduler scheduler;
    private double coeff_1 = 1;
    private Variable<Double> resources_value;
    private Variable<Double> resources_max;

    public ResourcesBehaviour(Map<String, Object> planet)
    {
        this.planet = planet;
        resources = (Map<String, Object>)planet.get("resources");
        resources_value = (Variable<Double>)resources.get("value");
        resources_max = (Variable<Double>)resources.get("max");
        scheduler = new Scheduler();
    }
    
    public void action()
    {
        if (scheduler.mayExecute())
        {
            if (resources_value.value < resources_max.value)
            {
                resources_value.value += coeff_1;
            }
            System.out.println("resources = " + resources_value.value);
            scheduler.addDelay(delay);
        }
        scheduler.block(this);
    }

    public boolean done()
    {
        return false;
    }
}
