package module.hello.cn.hello;

/**
 * cd module1
 * javac -d modules --module-source-path src src/module.hello/module-info.java src/module.hello/cn/hello/HelloWorld.java
 * jar --create --file libs/module.hello-1.0.jar --main-class cn.hello.HelloWorld -C modules/module.hello .
 * java --module-path libs/module.hello-1.0.jar --module module.hello
 * jlink --module-path modules --add-modules module.hello --launcher hello=module.hello/cn.hello.HelloWorld --output module-hello-image
 */
public class HelloWorld {

    public static void main(String[] args) {
        Class<HelloWorld> cls = HelloWorld.class;
        Module module = cls.getModule();
        String moduleName = module.getName();
        System.out.println("Module Name: " + moduleName);
    }

    public void print() {
        System.out.println("Hello java module");
    }
}