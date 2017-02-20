package com.example.tusharking.systemfreeze;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by TUSHARKING on 9/1/2016.
 */
public class RootCmd {
    String[] cmds;

    public void execute(String[] cmds) throws IOException {
        this.cmds = cmds;
        Process p = Runtime.getRuntime().exec("su");
        DataOutputStream os =new DataOutputStream(p.getOutputStream());
        for(String tmpCmd : cmds)
        {
            os.writeBytes(tmpCmd+"\n");
        }
        os.writeBytes("exit\n");
        os.flush();
    }

}
