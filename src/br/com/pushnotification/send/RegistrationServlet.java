package br.com.pushnotification.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pushnotification.persistence.DeviceRepository;

  @WebServlet("/doRegister")
  
  public class RegistrationServlet extends HttpServlet {
	  
	  private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException {

    System.out.println("RegistrationServlet.doPost");      
    String deviceId       = request.getParameter("deviceid");    
    String registrationId = request.getParameter("registrationid");

      System.out.println("RegistrationServlet.doPost->Registrando(");
      System.out.println("deviceid="+ deviceId);
    System.out.println("registrationid="+registrationId+")");

     String serverDir = getServletContext().getRealPath("/");
     DeviceRepository.saveDevice(serverDir, deviceId, registrationId);
   }
 }
