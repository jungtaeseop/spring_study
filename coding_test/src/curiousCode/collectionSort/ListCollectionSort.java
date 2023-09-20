package curiousCode.collectionSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCollectionSort {

    public static void main(String[] args){
        stringListTest();
        listinlistSort();
    }


    private static void stringListTest(){
        List<String> arr = Arrays.asList("1", "2");

        /**
         * a는 value 값이 때문에 value 에다가 4를 넣어 봤자 안된다. 의미 없다
         */
        for(String a : arr){
            a = "4";
        }

        System.out.println(arr);
    }


    public static void listinlistSort() {

        List<Integer> arr = Arrays.asList(2,1,3,44,55,12,13,0);

        List<List<Integer>> brr = Arrays.asList(arr);

        /**
         * 이 부분은 모든 하위 리스트(brr 내부의 각각의 리스트)에 대해 오름차순으로 정렬하는 작업을 수행합니다.
         * 람다 표현식 (integer, anotherInteger) -> integer.compareTo(anotherInteger) 는
         * 두 개의 Integer 객체가 주어지면 첫 번째 객체(integer)가 두 번째 객체(anotherInteger)보다 작으면 음수를 반환하고 같으면 0을 반환하며 크면 양수를 반환합니다.
         * 이런 방식으로 Java의 sort 메서드는 오름차순 정렬을 수행합니다.
         * */
        for (List<Integer> c : brr) {
            c.sort((integer, anotherInteger) -> integer.compareTo(anotherInteger));
            //c.sort(Integer::compareTo);
        }

        arr.forEach(System.out::println);
    }

}
