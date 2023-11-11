package com.intecsec.java.basic.designpattern.command;

import com.intecsec.java.basic.designpattern.command.cmdimp.*;
import com.intecsec.java.basic.designpattern.command.receiver.Light;
import com.intecsec.java.basic.designpattern.command.receiver.TV;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 22:00
 **/
public class Client {
    public static void main(String[] args) {
        mutil();
    }

    public static void onOff() {
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        Invoker invoker = new Invoker();
        invoker.setCommand(1, lightOnCommand, lightOffCommand);
        invoker.on(1);

        TV tv = new TV();
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);
        invoker.setCommand(2, tvOnCommand, tvOffCommand);
        invoker.on(2);

        invoker.off(2);
        invoker.off(1);
    }

    public static void undo() {
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        Invoker invoker = new Invoker();
        invoker.setCommand(1, lightOnCommand, lightOffCommand);
        invoker.on(1);
        invoker.undo();

        TV tv = new TV();
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);
        invoker.setCommand(2, tvOnCommand, tvOffCommand);
        invoker.on(2);
        invoker.undo();
    }

    public static void mutil() {
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        TV tv = new TV();
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        Command[] commandsOn = {lightOnCommand, tvOnCommand};
        MultiCommand multiCommand = new MultiCommand(commandsOn);
        multiCommand.execute();
        multiCommand.undo();
    }
}
