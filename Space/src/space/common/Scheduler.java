/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author Sergey
 */
public class Scheduler
{
    private long scheduledTime;
    
    public Scheduler()
    {
        scheduledTime = System.currentTimeMillis();
    }
    
    public Scheduler(long scheduledTime)
    {
        this.scheduledTime = scheduledTime;
    }
    
    public long getScheduledTime()
    {
        return scheduledTime;
    }
    
    public void setScheduledTime(long scheduledTime)
    {
        this.scheduledTime = scheduledTime;
    }
    
    public boolean mayExecute()
    {
        return scheduledTime - System.currentTimeMillis() <= 0;
    }
    
    public void addDelay(long delay)
    {
        if (delay > 0)
        {
            scheduledTime += delay;
        }
    }
    
    public void block(Behaviour behaviour)
    {
        long delay = scheduledTime - System.currentTimeMillis();
        if (delay > 0)
        {
            behaviour.block(delay);
        }
    }
}
