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
public class MovingBehaviour extends Behaviour
{
    private Map<String, Object> planet;
    private int delay = 100;
    private Scheduler scheduler;
    private Variable<Double> x;
    private Variable<Double> y;
    private Variable<Double> angular_velocity;
    private double r;
    private double phi;
    private int timer = 0;
    private int period = 20;

    public MovingBehaviour(Map<String, Object> planet)
    {
        this.planet = planet;
        Map<String, Variable<Double>> coordinates = (Map<String, Variable<Double>>)planet.get("coordinates");
        x = coordinates.get("x");
        y = coordinates.get("y");
        r = Math.sqrt(x.value * x.value + y.value * y.value);
        phi = Math.atan2(y.value, x.value);
        Map<String, Variable<Double>> velocities = (Map<String, Variable<Double>>)planet.get("velocities");
        angular_velocity = velocities.get("angular_velocity");
        scheduler = new Scheduler();
    }
    
    public void action()
    {
        if (scheduler.mayExecute())
        {
            phi += angular_velocity.value * (double)delay / 1000.0;
            x.value = r * Math.cos(phi);
            y.value = r * Math.sin(phi);
            timer = (timer + 1) % period;
            if (timer == 0)
            {
                System.out.println("x = " + x.value.toString() + " y = " + y.value.toString());
            }
            scheduler.addDelay(delay);
        }
        scheduler.block(this);
    }

    public boolean done()
    {
        return false;
    }
}
