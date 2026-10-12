import java.util.*;

public class Main {
    public static void main(String[] args) {
//?task1
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> list = numbers.stream().
                filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toList();
        System.out.println(list);

//?task2
        List<String> word = Arrays.asList("su", "od", "kulek", "yagis", "qar", "Cersenbe", "Qubalibaloglan", "Qubalibaloglan");
        List<String> list1 = word.stream()
                .distinct()
                .filter(n -> n.length() > 3)
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(3)
                .toList();
        System.out.println(list1);

//?task 3
 List<Employee> employees =Arrays.asList(
         new Employee("Nicat", "IT", 2500, 25),
         new Employee("Orxan", "HR", 1800, 32),
         new Employee("Leyla", "IT", 3200, 35),
         new Employee("Murad", "Finance", 2200, 28));

 Optional<Employee> salary =employees.stream()
                 .max(Comparator.comparingDouble(e-> e.getSalary()));
        System.out.println("max salary: "+salary);
//!Task3 :Employee sinfi var: name, department, salary, age.
//!Bu siyahıdan:
//!ən yüksək maaşlı işçini tap (Optional ilə),
//!bütün işçilərin orta maaşını hesabla,
//!yaşı 30-dan böyük olanların ümumi maaş cəmini tap.
//!
//!Task4 :Eyni Employee siyahısından:
//!işçiləri department-ə görə qruplaşdır (Map<String, List<Employee>>),
//!hər departamentdə neçə işçi olduğunu çıxar (Map<String, Long>),
//!hər departamentin orta maaşını tap.
//!
//?task5
        List<String> ePoct = Arrays.asList("NICAT@GMAIL.COM", "ALI@YAHOO.COM", "NICAT@GMAIL.COM", "MURAD@OUTLOOK.COM", "ALI@YAHOO.COM", "AYSEN@GMAIL.COM");
        List<String> ePoct1 = ePoct.stream()
                .map(n -> n.toLowerCase())//hamisini balaca herf ile capa verir
                .filter(n -> n.contains("@gmail.com")) //@gmail.com ile bitenleri secir
                .distinct()//tekrarlananlari silir
                .toList();
        System.out.println(ePoct1);
        long count = ePoct.stream()
                .distinct()
                .count();
        System.out.println(count);

//?Task 6
        List<String> student = Arrays.asList("Aysel", "Orxan", "Leyla");
        student.stream()
                .map(s -> "telebe: " + s)
                .forEach(System.out::println);
//?Task 7
        List<Integer> numberss = Arrays.asList(5, 12, 8, 15, 20);
        List<Integer> numberss1 = numberss.stream()
                .filter(n -> n > 10)
                .toList();
        numberss1.stream()
                .map(s -> "Eded: " + s)
                .forEach(System.out::println);

//? task 8
        Map<String, Integer> subject = Map.of(
                "Riyaziyyat", 90,
                "Fizika", 78,
                "Kimya", 67);
        subject.entrySet().stream()
                .forEach(System.out::println);

    }
}