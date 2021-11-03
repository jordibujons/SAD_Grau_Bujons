import java.io.IOException;


public class match {

    private boolean match(String str) throws IOException {
        boolean b = true;
        mark(str.length()); // podem llegir fins la longitud de str i tirar enrere si convé

        for (int i = 0; i < str.length(); i++) {
            b = str.charAt(i) == super.read();
            if (!b || !ready() && i < str.length() - 1) { // ready comproba que tinguid contigut en els dos strings per
                                                          // no seguir comparant quan un dels dos ja s'ha acabat
                reset(); // tornar a llegir des del principi
                break;
            }
        }
        return b;
    }
}