package org.base;

import org.graalvm.polyglot.Value;

import java.util.function.Function;

public class SomeInterface {

    @Api
    public void test(Function<Object, Void> handler) {
        System.out.println("Calling handler.apply with pure string array with one value");
        handler.apply(new String[]{"arg1"});

        System.out.println("\nCalling handler.apply with pure string array with two values");
        handler.apply(new String[]{"arg1", "arg2"});

        System.out.println("\nCalling handler.apply with string array with one value, wrapped with Value.asValue");
        handler.apply(Value.asValue(new String[]{"arg1"}));

        System.out.println("\nCalling handler.apply with string array with two values, wrapped with Value.asValue");
        handler.apply(Value.asValue(new String[]{"arg1", "arg2"}));

        System.out.println("\nCalling handler.apply with string array with one value, wrapped with Value.asValue, then unwrapped with as(String[].class)");
        handler.apply(Value.asValue(new String[]{"arg1"}).as(String[].class));

        System.out.println("\nCalling handler.apply with string array with two values, wrapped with Value.asValue, then unwrapped with as(String[].class)");
        handler.apply(Value.asValue(new String[]{"arg1", "arg2"}).as(String[].class));
    }
}
