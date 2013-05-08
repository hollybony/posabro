/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.SystemParameter;
import com.posabro.ocsys.domain.SystemParameterId;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface SystemParameterService {
    
    public SystemParameter findSystemParameter(SystemParameterId id);
    
    public List<SystemParameter> getAllSystemParameters();
    
    public void saveSystemParameter(SystemParameter systemParameter);
    
    public void updateSystemParameter(SystemParameter systemParameter);
    
    public void removeSystemParameter(SystemParameterId id);
    
}
