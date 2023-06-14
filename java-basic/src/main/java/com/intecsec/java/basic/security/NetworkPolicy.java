package com.intecsec.java.basic.security;

import java.security.Permission;
import java.security.Policy;
import java.security.ProtectionDomain;

public class NetworkPolicy extends Policy{

	private final Permission permissions;

    public NetworkPolicy(Permission permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean implies(ProtectionDomain domain, Permission permission) {
        return permissions.implies(permission);
    }
}
