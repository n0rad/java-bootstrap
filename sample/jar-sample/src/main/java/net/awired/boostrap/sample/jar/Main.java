package net.awired.boostrap.sample.jar;

import net.awired.aclm.argument.CliArgumentManager;
import net.awired.aclm.argument.args.CliOneParamArgument;
import net.awired.aclm.param.CliParamString;

public class Main {

    public class ArgumentManager extends CliArgumentManager {
        private CliOneParamArgument<String> typeArgument;

        public ArgumentManager() {
            super("sample-jar");

            typeArgument = new CliOneParamArgument<String>('t', new CliParamString("type"));
            typeArgument.setParamOneDefValue("default");
            addArg(typeArgument);
        }
    }

    public Main(String[] args) {
        ArgumentManager am = new ArgumentManager();
        am.parse(args);

        System.out.println("type argument value is : " + am.typeArgument.getParamOneValue());
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
