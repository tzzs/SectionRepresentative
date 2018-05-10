import java.util.List;
import java.util.Random;

public class getHno {
    public static int gethno() {
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        homeworkDao hd = new homeworkDao();
        List<String> stringList = hd.getAllHno();

        int r = max;
        do {
            r = random.nextInt(max) % (max - min + 1) + min;
        } while (stringList.indexOf(Integer.toString(r)) != -1);
        return r;
    }

//    public static void main(String args[]) {
//        int r = gethno();
//        System.out.println(r);
//    }
}
