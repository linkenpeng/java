package com.intecsec.java.basic.designpattern.command.cmdimp;

import com.intecsec.java.basic.designpattern.command.Command;
import com.intecsec.java.basic.designpattern.command.receiver.TV;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 21:59
 **/
public class TVOnCommand implements Command {
    TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
        tv.setVolume(11);
    }

    @Override
    public void undo() {
        tv.off();
    }
}
