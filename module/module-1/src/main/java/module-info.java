/**
 * cd module-1
 * javac -d modules --module-source-path src java/module-info.java java/com/intecsec/module1/hello/HelloWorld.java
 * jar --create --file libs/module1-1.0.jar --main-class com.intecsec.module1.hello.HelloWorld -C modules/module1 .
 * java --module-path libs/module1-1.0.jar --module module1
 * jlink --module-path modules --add-modules module1 --launcher hello=module1/com.intecsec.module1.hello.HelloWorld --output module-hello-image
 */
module module1 {
// open module module1 {
    exports com.intecsec.module1.hello;
    exports com.intecsec.module1.open;
    exports com.intecsec.module1.first.p1;

    opens com.intecsec.module1.open;
    //	provides com.intecsec.module1.first.p1.Shoe
    //	with com.intecsec.module1.first.p2.DoubleStar,com.intecsec.module1.first.p2.Warrior;

    provides com.intecsec.module1.first.p1.Shoe
            with com.intecsec.module1.first.p1.ShoeFactory;
}