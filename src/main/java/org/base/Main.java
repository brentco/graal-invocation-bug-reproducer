package org.base;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

import java.time.chrono.ChronoZonedDateTime;

public class Main {
    public static void main(String[] args) {
        Context ctx = createPolyglotContext();
        ctx.getBindings("js").putMember("Test", new SomeInterface());
        ctx.eval("js", "Test.test(arr => {" +
                "console.log('contents =', arr, 'isArray =', Array.isArray(arr), 'length =', arr.length);" +
                "});");
    }

    private static Context createPolyglotContext() {
        HostAccess.Builder builder = HostAccess.newBuilder()
                .allowAccessAnnotatedBy(Api.class)
                .allowPublicAccess(false)
                .allowArrayAccess(true)
                .allowIterableAccess(true)
                .allowIteratorAccess(true)
                .allowImplementationsAnnotatedBy(FunctionalInterface.class)
                .allowImplementations(ChronoZonedDateTime.class);

        HostAccess accessConfig = builder.build();

        Context.Builder contextBuilder = Context.newBuilder("js");
        contextBuilder.allowExperimentalOptions(true)
                .option("js.graal-builtin", "false")
                .option("js.polyglot-builtin", "false")
                .option("js.foreign-object-prototype", "true")
                .option("js.ecmascript-version", "6")
                .option("js.strict", "true")
                .option("js.regexp-static-result", "false")
                .option("js.java-package-globals", "false")
                .option("js.global-property", "false")
                .option("js.console", "true")
                .option("js.performance", "false")
                .option("js.print", "false")
                .option("js.load", "false")
                .option("js.disable-eval", "true")
                .allowHostAccess(accessConfig);


        return contextBuilder.build();
    }
}
