package com.intecsec.java.basic.designpattern.command.cmdimp;

import com.intecsec.java.basic.designpattern.command.Command;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-11 11:20
 **/
public class MultiCommand implements Command {
    Command[] commands;

    public MultiCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for(int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        for(int i = 0; i < commands.length; i++) {
            commands[i].undo();
        }
    }
}
