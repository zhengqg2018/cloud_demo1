package com.zqg.consumer_one.utils;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author: zqg
 * @create: 2020/12/16
 **/
public class Demo {

    private Object renderedObject = null;

    private Consumer<Object> fnResolve = object -> {
        renderedObject = object;
         System.out.println("fnResolve=>promiseResolved");
    };

    private Consumer<Object> fnRejected = object -> {
        renderedObject = object;
        System.out.println("fnRejected=>promiseRejected");
    };

    private void execute() {
        try (Context context = Context.newBuilder("js").allowAllAccess(true).build()) {
            Value eval = context.eval("js",
                    "(async function() { return 'Hello World' });"
            );
            eval.execute().invokeMember("then", fnResolve, fnRejected);

            System.out.println("renderedObject = " + renderedObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo main = new Demo();
        main.execute();
    }

}