module module2 {
    exports com.intecsec.module2.hello;
    exports com.intecsec.module2.open;

    // transitive 将依赖的包也加进来
    requires transitive module1;
    // requires module1;

    uses com.intecsec.module1.first.p1.Shoe;
}