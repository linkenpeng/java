/**
 * java --list-modules
 * java --describe-module java.base
 *
 * cd module3
 * javac -d modules --module-source-path src src/main/java/module-info.java src/main/java/com/module3/HelloWorld.java
 * jar --create --file libs/module.hello-1.0.jar --main-class com.module3.HelloWorld -C modules/module.hello .
 * java --module-path libs/module.hello-1.0.jar --module module3
 * jlink --module-path modules --add-modules module3 --launcher hello=module3/com.module3.HelloWorld --output module-hello-image
 */

module module3 {
    exports com.module3;
}