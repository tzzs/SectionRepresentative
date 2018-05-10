package password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class secure {
    public static String Encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] digest) {
        StringBuilder des = new StringBuilder();
        String tmp = null;
        for (byte aDigest : digest) {
            tmp = Integer.toHexString(aDigest & 0xFF);
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
}
