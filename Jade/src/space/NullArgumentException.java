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
public class NullArgumentException extends Exception
{
    private String paramName;
    
    public NullArgumentException(String paramName)
    {
        if (paramName == null)
        {
            this.paramName = "";
        }
        else
        {
            this.paramName = paramName;
        }
    }
    
    public String getParamName()
    {
        return paramName;
    }
}
