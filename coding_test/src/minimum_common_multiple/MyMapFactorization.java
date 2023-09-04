package minimum_common_multiple;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class MyMapFactorization {
    public static void main(String[] args) {
        
        int[] answer = solution(50,32,23,48);
        //System.out.println(Arrays.toString(answer));
        int[] answer2 = solution2(50,32,23,48);
        System.out.println(Arrays.toString(answer));
        System.out.println(Arrays.toString(answer2));

        /*int countDifferentResults = 0;
        for (int numer1 = 1; numer1 <= 50; numer1++) {
            for (int denom1 = 1; denom1 <= 50; denom1++) {
                for (int numer2 = 1; numer2 <= 50; numer2++) {
                    for (int denom2 = 1; denom2 <= 50; denom2++) {
                        int[] answer = solution(numer1, denom1, numer2, denom2);
                        int[] answer2 = solution2(numer1, denom1, numer2, denom2);

                        if (answer[0] != answer2[0] || answer[1] != answer2[1]) {
                            countDifferentResults++;
                            System.out.println("다른 결과: (" + numer1 + "/" + denom1 + ") + (" + numer2 + "/" + denom2 + ")");
                            System.out.println("solution 결과: " + Arrays.toString(answer));
                            System.out.println("solution2 결과: " + Arrays.toString(answer2));
                            System.out.println();
                        }
                    }
                }
            }
        }
        System.out.println("다른 결과 개수: " + countDifferentResults);*/
    }

    /**
     * 다른 사람이 푼 방식
     * */
    private static int[] solution2(int numer1, int denom1, int numer2, int denom2) {
        int numerator = (numer1 * denom2) + (numer2 * denom1);
        int denominator = denom1 * denom2;

        for(int i = numerator-1; i > 1; i--) {
            // 기약분수로 만들기
            if(numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
        }

        int[] answer = { numerator, denominator };
        return answer;
    }

    /**
     * 내가 푼 문제
     * */
    static int[] solution(int numer1, int denom1, int numer2, int denom2) {

        List<Integer> denomMultiplication = Arrays.asList(denom1,denom2);
        Map<Integer,Integer> primeFactor = new HashMap<>();

        for(int item : denomMultiplication){
            Map<Integer,Integer> minorFactor = factorization(item);
            minorFactor.forEach((key,value) -> {
                primeFactor.put(key, Math.max(primeFactor.getOrDefault(key,1),value));
            });
        }

        Integer leastCommonMultiple = primeFactor.entrySet().stream()
                .mapToInt(entry -> (int) Math.pow(entry.getKey(),entry.getValue()))
                .reduce(1,(mul,entry) -> mul*entry);



        Integer molecular = numer1 * (leastCommonMultiple / denom1);
        Integer molecular2 = numer2 * (leastCommonMultiple / denom2);

        Integer sumMolecules = molecular + molecular2;


        for(int item = 2 ; item <= Math.max(sumMolecules,leastCommonMultiple);item ++){
            if ( sumMolecules % item == 0 && leastCommonMultiple % item == 0){
                sumMolecules /= item;
                leastCommonMultiple /= item;
                item = 1;
            }
        }
        /*System.out.println(sumMolecules + ", "+leastCommonMultiple);*/
        int[] answer = {sumMolecules,leastCommonMultiple};

        return answer;

    }

    /**
     * 소인수분해 최소공배수 구하는 함수
     * */
    private static Map<Integer,Integer> factorization(int item){

        Map<Integer,Integer> factors = new HashMap<>();

        int moc = item;

        for(int dividing = 2 ; dividing <= item; dividing++){
            if(moc % dividing == 0){
                moc = moc/dividing;
                factors.put(dividing, factors.getOrDefault(dividing , 0) +1 );
                dividing--;
            }
        }

        return factors;
    }
}
