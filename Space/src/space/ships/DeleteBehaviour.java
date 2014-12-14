/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.ships;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author Sergey
 */
public class DeleteBehaviour extends Behaviour
{
    private String msg;
    
    public DeleteBehaviour(String msg)
    {
        this.msg = msg;
    }
    
    public void action()
    {
        if (msg != null)
        {
            System.out.println(msg);
        }
        myAgent.doDelete();
    }

    public boolean done()
    {
        return true;
    }
}
