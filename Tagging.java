interface Confidential {
}

class PublicDocument {
    String name;

    PublicDocument(String name) {
        this.name = name;
    }
}

class PrivateDocument implements Confidential {
    String name;

    PrivateDocument(String name) {
        this.name = name;
    }
}

public class Tagging {
    public static void main(String[] args) {
        Object d1 = new PublicDocument("College Notice");
        Object d2 = new PrivateDocument("Salary Details");

        if (d1 instanceof Confidential) {
            System.out.println("Document 1 is Confidential");
        } else {
            System.out.println("Document 1 is not Confidential");
        }

        if (d2 instanceof Confidential) {
            System.out.println("Document 2 is Confidential");
        } else {
            System.out.println("Document 2 is not Confidential");
        }
    }
}