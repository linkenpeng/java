package com.intecsec.java.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-07-19 14:24
 **/
public class Args {

    private Args() {
    }

    public static void check(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void check(final boolean expression, final String message, final Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static <T extends Map<?, ?>> T notEmpty(final T argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
        return argument;
    }

    public static <T> T notNull(final T argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        return argument;
    }

    public static <T extends CharSequence> T notEmpty(final T argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        if (StringUtils.isEmpty(argument)) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
        return argument;
    }

    public static <T extends CharSequence> T notBlank(final T argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        if (StringUtils.isBlank(argument)) {
            throw new IllegalArgumentException(name + " may not be blank");
        }
        return argument;
    }

    public static <E, T extends Collection<E>> T notEmpty(final T argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
        return argument;
    }

    public static <T> T[] notEmpty(final T[] argument, final String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
        if (0 >= argument.length) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
        return argument;
    }

    public static boolean eqNumber(final Integer n1, final Integer n2) {
        return (null != n1 && null != n2 && n1.intValue() == n2.intValue());
    }

    public static boolean eqNumber(final Long n1, final Long n2) {
        return (null != n1 && null != n2 && n1.longValue() == n2.longValue());
    }

    public static boolean noEqNumber(final Integer n1, final Integer n2) {
        if (null == n1 && null == n2) {
            return false;
        }
        return !(eqNumber(n1, n2));
    }

    public static boolean noEqNumber(final Long n1, final Long n2) {
        if (null == n1 && null == n2) {
            return false;
        }
        return !(eqNumber(n1, n2));
    }

    public static boolean isPositive(final Integer n) {
        return (null != n && 0 < n);
    }

    public static boolean isPositive(final Long n) {
        return (null != n && 0 < n);
    }

    public static int positive(final int n, final String name) {
        if (n <= 0) {
            throw new IllegalArgumentException(name + " may not be negative or zero");
        }
        return n;
    }

    public static long positive(final long n, final String name) {
        if (n <= 0) {
            throw new IllegalArgumentException(name + " may not be negative or zero");
        }
        return n;
    }

    public static int notNegative(final int n, final String name) {
        if (n < 0) {
            throw new IllegalArgumentException(name + " may not be negative");
        }
        return n;
    }

    public static long notNegative(final long n, final String name) {
        if (n < 0) {
            throw new IllegalArgumentException(name + " may not be negative");
        }
        return n;
    }


}
