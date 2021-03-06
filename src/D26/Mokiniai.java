package D26;
//static variantas
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mokiniai {

    public static void main(String[] args) {
// suskaiciuoti kiek yra mokiniu pagal metus
        List<Mokinys> mokiniai = new ArrayList<>();
        mokiniai.add(new Mokinys(1001, "V1", "P1", LocalDate.of(2000, 1, 1), 8));
        mokiniai.add(new Mokinys(1002, "V2", "P2", LocalDate.of(2001, 4, 2), 6));
        mokiniai.add(new Mokinys(1003, "V3", "P3", LocalDate.of(2002, 5, 3), 5));
        mokiniai.add(new Mokinys(1004, "V4", "P4", LocalDate.of(2003, 6, 4), 7.6));
        mokiniai.add(new Mokinys(1005, "V5", "P5", LocalDate.of(2004, 7, 5), 9.6));
        mokiniai.add(new Mokinys(1006, "V6", "P6", LocalDate.of(2005, 8, 6), 5));

        //
        Map<Integer, Integer> m = new HashMap<>();
        mokiniai.forEach(mokinys -> {
            int metai = mokinys.getBirthday().getYear();
            if (m.containsKey(metai)) {
                int counter = m.get(metai);
                counter++;
                m.put(metai, counter);
            } else {
                m.put(metai, 1);
            }

        });
        System.out.println(m);
        // suskaiciuoti kiek mokiniu yra pagal metus ir koks ju pazymiu vidurkis

        Map<Integer, CntAvg>  m2 = calc2(mokiniai);

        System.out.println(m2);

        System.out.println(m2.get(2002).getClass().getName());

        System.out.println(m2.get(2002).average);

        Mokinys mok1 = mokiniai.get(0);
        mok1.setSex(new Mokinys.Sex());
    }

    static Map<Integer, CntAvg> calc2(List<Mokinys> mokiniai) {

        Map<Integer, CntAvg> m2 = new HashMap<>();
        mokiniai.forEach((Mokinys mokinys) -> {
            int metai = mokinys.getBirthday().getYear();
            if (m2.containsKey(metai)) {
                CntAvg value = m2.get(metai);
                value.counter++;
                value.average = (value.average * (value.counter - 1) + mokinys.getAverage()) / value.counter;
            } else {
                m2.put(metai, new CntAvg(1, mokinys.getAverage()));

            }

        });
        return m2;
    }
     static class CntAvg {
        int counter;
        double average;

        CntAvg(int counter, double average) {
            this.counter = counter;
            this.average = average;
        }

        @Override
        public String toString() {
            return
                    "counter=" + counter +
                            ", average=" + average;
        }
    }




}