package member.util;

import org.mindrot.jbcrypt.BCrypt;

public class Crypt {
    public static String encrypt(String plainPW) {
        return BCrypt.hashpw(plainPW, BCrypt.gensalt());
    }

    public static boolean decrypt(String plainPW, String encrypt) {
        return BCrypt.checkpw(plainPW, encrypt);
    }

}
