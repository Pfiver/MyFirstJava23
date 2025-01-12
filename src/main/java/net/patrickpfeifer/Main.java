void main() {

    try (var writer = System.console().writer()) {

        writer.println("Hello World!");
        writer.flush();
    }

    assert (false);

    var yes = (1 instanceof int i);

    var b = true;

    if (yes && (1 instanceof int i)) {

        System.out.println(i);
    }
    else {
//        System.out.println(i);
    }
}
