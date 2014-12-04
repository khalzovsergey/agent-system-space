/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

/**
 *
 * @author Sergey
 */
public class Scheduler
{
    private long time;
    
    public Scheduler()
    {
        time = System.currentTimeMillis();
    }
    
    public Scheduler(long start)
    {
        time = start;
    }
    
    public long getDelay()
    {
        long result = time - System.currentTimeMillis();
        if (result < 0)
        {
            result = 0;
        }
        return result;
    }
    
    public void next(long shift)
    {
        time += shift;
    }
    
    public boolean execute()
    {
        return time - System.currentTimeMillis() <= 0;
    }
}
