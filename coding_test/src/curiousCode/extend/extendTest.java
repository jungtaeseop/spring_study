package curiousCode.extend;

public class extendTest {
    public static void main(String[] args) {
        SuperObject a = new SubObject();
        a.paint();
    }
}


class SuperObject {
    public void paint() {
        draw();
    }

    public void draw() {
        draw();
        System.out.println("Super");
    }
}

class SubObject extends SuperObject {
    @Override
    public void paint() {
        super.paint();
    }

    @Override
    public void draw() {
        System.out.println("Sub");
    }
}
