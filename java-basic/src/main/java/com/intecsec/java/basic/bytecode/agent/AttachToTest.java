package com.intecsec.java.basic.bytecode.agent;

import java.util.List;


import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class AttachToTest {

	public static void main(String[] args)  {
		List<VirtualMachineDescriptor> vmds = VirtualMachine.list();

		try {
			for(VirtualMachineDescriptor vmd:vmds)
			{
				if(vmd.displayName().equals("com.intecsec.java.basic.bytecode.agent.Test"))
				{
					VirtualMachine vm = VirtualMachine.attach(vmd.id());
					vm.loadAgent("agent/target/agent-0.0.1-SNAPSHOT.jar", "argument for agent");
					System.out.println(vmd.displayName() + "load ok");
					vm.detach();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
