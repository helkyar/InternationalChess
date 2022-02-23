/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinechess.controller.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import onlinechess.controller.session.InputValidator;
import onlinechess.views.Session;
import packager.Package;

/**
 *
 * @author admin
 */
class Response implements Runnable{
       
    Response(){
        Thread lintening = new Thread(this);
        lintening.start();
    }

    @Override
    public void run() {
        packager.Package p;
        String nick, move, msg;
        ObjectInputStream input;
        ServerSocket port = null;

        try {port = new ServerSocket(9090);}
        catch (IOException ex) {ex.printStackTrace();}

        while(true){
            try (Socket request = port.accept()) {
                input = new ObjectInputStream(request.getInputStream());
                p = (packager.Package) input.readObject();
                request.close();

                switch(p.getStatus()){
                    case "login":    setLoginMessage(p); break;
                    case "register": setRegisterMessage(p); break;                        
//                        case "getusers": setUsersOnline(p); break;
//                        case "message":  sendMessage(p); break;
//                        case "managegroup": serverMembersResponse(p); break;
//                        case "groupusers":  informChatUsers(p); break;                       
//                        case "changeusers": refreshGroups(p); break;                
                }
            } catch(Exception e){e.printStackTrace();}                    
        }
    }    
    //Gets server response about login/register and sends it accordingly

    private void setLoginMessage(Package p) {
        InputValidator.serverLoginValidator(p.getInfo(),p.getNick(), p.getId());
    }

    private void setRegisterMessage(Package p) {
        InputValidator.svrRegisterValidator(p.getInfo(), p.getNick(), p.getId());
    }
}
    
