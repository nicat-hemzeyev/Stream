import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiFullTotarial {
    public static void main(String[] args) {
        basic_stream_creation();
        basic_filter();
        basic_map();
        basic_sorted_distinct();
        basic_limit_skip();
        terminal_count_min_max();
        terminal_find_match();
        collect_toList_toSet_joining();
        collect_grouping_partitioning();
        reduce_sum_product();
        primitive_streams_mapToInt_sum_avg();
        floatMap_nestedLists();
    }

//    bu metodlar 2 yere ayrilir
//    1.INTERMATIDE OPERATIONS --yeni strem qaytarir
//    2.TERMINAL OPERATIONS
    static void basic_stream_creation() {
        System.out.println("---basic_stream_creation---");
        List<String> list = List.of("Ali", "Nijat", "Aysel");
        Stream<String> s1 = list.stream();               //Stream yaratmagin 1 ci usulu
        Stream<String> s2 = Stream.of("a", "b");//stream yaratmagin 2-ci usulu
        int[] arr = {1, 2, 3};
        IntStream s3 = Arrays.stream(arr);             //steram yaratmagin 3 cu usulu;
        System.out.println(s1.toList());
        System.out.println(s2.toList());
        System.out.println(s3.boxed().toList());
    }

    static void basic_filter() {//sade filtirleme emeliyyati gedir
        System.out.println("\n---basic_filter---");
        // List<Integer>evens =List.of(1,2,3,4,5,6).stream()
        List<Integer> evens = Stream.of(1, 2, 3, 4, 5, 6)
                .filter((x) -> x % 2 == 0)
                .toList();
        System.out.println(evens);
    }

    static void basic_map() {
        System.out.println("\n---basic_map---");
        List<Integer> lenghts = Stream.of("aaa", "bb", "c")//stringlerin uzunluqlarini capa verir.
                .map(String::length)//map metodu
                .toList();
        System.out.println(lenghts);
    }

    static void basic_sorted_distinct() {
        System.out.println("\n---basic_sorted_distinct---");
        // List<Integer> result =List.of(5,1,3,3,2,1).stream()
        List<Integer> result = Stream.of(5, 1, 3, 3, 2, 1)
                .distinct() //tekrarlanan elemenleri silir
                .sorted() //siralayir
                .toList();
        //intermatide operations
        System.out.println(result);
    }

    static void basic_limit_skip() {
        System.out.println("\n---basic_limit_skip---");
        List<Integer> page = IntStream.rangeClosed(1, 50)
                .skip(10) // 1 den 10-a kimi ededleri goturmeyecek
                .limit(5)//10-dan 15-e kimi yeni 5 dene eded  goturecek
                .boxed()//sonra gotur hamisini boxed ele integere cevir
                .toList();
        System.out.println(page);
    }

    static void terminal_count_min_max() {
        System.out.println("\n---terminal_count_min_max---");
        List<Integer> nums = List.of(1, 2, 3, 4);
        long count = nums.stream().count(); //element sayini capa verir
        int min = nums.stream().min(Integer::compareTo).orElseThrow();
        int max = nums.stream().max(Integer::compareTo).orElseThrow();
        System.out.println(count);
        System.out.println(min);
        System.out.println(max);
    }

    static void terminal_find_match() {
        System.out.println("\n---terminal_find_match---");

        List<Integer> nums = List.of(1, 2, 3, 30);

        boolean anyBig = nums.stream().anyMatch(x -> x > 10);//listdeki ededlerini her hansi biri verilen serti odeyirmi?
        boolean allPositive = nums.stream().allMatch(x -> x > 0);//butun ededler 0-dan boyukdurmu? hamisi boyukdurse true qaytaracaq her hansi bir menfi olsa false qaytaracaq
        boolean noneNegative = nums.stream().noneMatch(x -> x > 0);//allmatch in tersidir hec bir eded 0 dan kicik deyil deyir false qaytaracaq
        Optional<Integer> first = nums.stream().findFirst();// ya arrayda 1 element var onda edirik yada coxlu element olmasina baxmayaraq  bize sadece birinci element lazimdir onu goturubu uzerinde emeliyyat edeceyik o zaman istifade edirik
        Optional<Integer> any = nums.stream().findAny();
        System.out.println("anyBig: " + anyBig);
        System.out.println("allPositive: " + allPositive);
        System.out.println("noneNegative: " + noneNegative);
        System.out.println("first: " + first);
        System.out.println("any: " + any);
    }

    static void collect_toList_toSet_joining() {
        System.out.println("\n---collect_toList_toSet_joining---");

        List<String> names = List.of("Ali", "Huseyn", "Nijat", "Aysel");
        /*bu elemenleri goturub sete cevirmek istreyirem  sete cevirmek  ucun names
       icinnden streami cagiriram ve streamin icinden collecti cagiriram
      */
        Set<String> set = names.stream().collect(Collectors.toSet());
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println("set= " + set);
        System.out.println("joined= " + joined);
    }

    static void collect_grouping_partitioning() {
        System.out.println("\n---collect_grouping_partitioning---");
        //elemenleri istediyiniz parametrlere gore qruplasdira bilersiz  yas ad vs

        List<User> users = demoUsers();

        Map<Integer, List<User>> byAge = users.stream()
                .collect(Collectors.groupingBy(User::age));

        Map<Boolean, List<User>> adults = users.stream()
                .collect(Collectors.partitioningBy(u -> u.age() >= 18));
        System.out.println(byAge);
        System.out.println(adults);

    }

    static void reduce_sum_product() {
        System.out.println("\n---reduce_sum_product---");
        //hansisa ededlerin cemi,fibonnacci ededler, factorial hesablamaq vs
        List<Integer> nums = List.of(1, 2, 3, 4);

        int sum = nums.stream().reduce(0, Integer::sum);
        int product = nums.stream().reduce(1, (a, b) -> a * b);
        System.out.println("sum: " + sum);
        System.out.println("product: " + product);

    }

    static void primitive_streams_mapToInt_sum_avg() {
        System.out.println("\n---primitive_streams_mapToInt_sum_avg---");

        List<String> words = List.of("a", "bbb", "cc");
        int sumLen = words.stream().mapToInt(String::length).sum();
        double avgLen = words.stream().mapToInt(String::length).average().orElse(0);
        System.out.println(sumLen);
        System.out.println(avgLen);
    }

    static void floatMap_nestedLists() {
        System.out.println("\n---floatMap_nestedLists---");
//listin icinde list olanda  onlari bir listde capa verir
        List<List<String>> nested =List.of(
                List.of("a","b"),
                List.of("c","d")
        );
        List<String> flat =nested.stream()
                        .flatMap(List::stream)
                                .toList();
        System.out.println(flat);
    }

    static void optional_stream_ofNullable() {}

    static void laziness_demo() {}

    static void real_world_topN() {}

    static void real_world_group_and_sum() {}

    static List<User> demoUsers() {
        return List.of(
                new User(1, "Nijat", 22, "Kuba"),
                new User(2, "Nihat", 19, "Kuba"),
                new User(3, "Ali", 25, "Baku"),
                new User(4, "Khabib", 30, "Dagistan"),
                new User(5, "Heydar", 35, "Samaki")
        );
    }

    public record User(long id, String name, int age, String city) {}


}

