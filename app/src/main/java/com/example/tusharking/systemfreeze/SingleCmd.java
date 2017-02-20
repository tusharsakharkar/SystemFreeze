package com.example.tusharking.systemfreeze;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by TUSHARKING on 9/26/2016.
 */
public class SingleCmd {

    String cmds;
    Process p;

    public void execute12(String cmds) throws IOException {
        this.cmds = cmds;
         p = Runtime.getRuntime().exec("su");
        p = Runtime.getRuntime().exec(cmds);
        DataOutputStream os = new DataOutputStream(p.getOutputStream());
        os.writeBytes("exit\n");
        os.flush();
    }
}