/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.wat.wel.javaapp;

/**
 *
 * @author pdaba
 */
public class Protocol {

    static String getCmd(int selectedCmd, String cmdValue, String cmdText, boolean cmdTypeSet) {
        StringBuilder cmd = new StringBuilder(":");
        CmdType selectedCmdType = CmdType.values()[selectedCmd];

        cmd.append(selectedCmdType.toString());
        if (cmdTypeSet) {
            cmd.append("=");
            cmd.append(cmdValue);
            cmd.append("\r\n");
            switch (selectedCmdType) {
                case LCD:
                    cmd.append(cmdText);
                    cmd.append("\0");
                    break;
            }
        }else{
            cmd.append("?");
            cmd.append("\r\n");            
        }
        
        return cmd.toString();
    }

    static boolean checkResponse(int selectedCmd, String response) {
        if (response.contains("OK")) {
            return true;
        } else if (response.contains("ERRO")) {
            return false;
        } else {
            return false;
        }
    }
}
