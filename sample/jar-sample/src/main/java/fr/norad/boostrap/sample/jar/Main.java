/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package fr.norad.boostrap.sample.jar;

import net.awired.aclm.argument.CliArgumentManager;
import net.awired.aclm.argument.args.CliOneParamArgument;
import net.awired.aclm.param.CliParamString;
import org.xerial.snappy.Snappy;

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

        Snappy.maxCompressedLength(4242);
        Snappy.getNativeLibraryVersion();
        System.out.println("OK!!");
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
