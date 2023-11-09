package com.intecsec.java.basic.designpattern.command;

import com.intecsec.java.basic.designpattern.command.cmdimp.NoCommand;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 22:02
 **/
public class Invoker {
    Command[] commandOns;
    Command[] commandOffs;

    public Invoker() {
        commandOns = new Command[3];
        commandOffs = new Command[3];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 3; i++) {
            commandOns[i] = noCommand;
            commandOffs[i] = noCommand;
        }
    }

    public void setCommand(int index, Command commandOn, Command commandOff) {
        this.commandOns[index] = commandOn;
        this.commandOffs[index] = commandOff;
    }

    public void on(int index) {
        this.commandOns[index].execute();
    }

    public void off(int index) {
        this.commandOffs[index].execute();
    }
}
