import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

public class Main {
    public static void main(String[] args) throws IOException {
        final List<String> primes = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            primes.addAll(readAllLines(get("src/main/resources/2T_part"+i+".txt"))
                    .parallelStream()
                    .map(line -> line.split("[\t\s]+"))
                    .flatMap(Stream::of)
                    .filter(string -> !string.isBlank())
                    .toList());
        }

        final List<String> pandigitalPrimes = primes.stream()
                .filter(prime -> {
                    final int[] digits = new int[10];
                    prime.chars()
                            .map(Character::getNumericValue)
                            .forEach(i -> digits[i]++);
                    for (int i = 1; i <= prime.length(); i++) {
                        if (digits[i] != 1) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();

        System.out.println("First prime " + primes.get(0));
        System.out.println("Number of primes " + primes.size());
        System.out.println("Max pandigital prime " + pandigitalPrimes.stream().max(String::compareTo));

    }
}
