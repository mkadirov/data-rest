package pdp.datarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniqueNumberGenerator {
    static  Random random;
    static Set<Integer> generatedNumbers;

    public static   int getNextUniqueNumber(){
        int num;
        do{
            num = random.nextInt();
        }while (generatedNumbers.add(num));

        return num;
    }
}

