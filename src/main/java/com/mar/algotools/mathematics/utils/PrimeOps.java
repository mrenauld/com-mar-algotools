package com.mar.algotools.mathematics.utils;

import java.math.BigInteger;
import java.util.ArrayList;

public class PrimeOps {

    /**
     * Returns all divisors (1 and itself included) of an integer.
     * @param n
     * @return
     */
    public static ArrayList<Integer> getDivisors(int n) {
        int limit = MathOps.floor(Math.sqrt(n));
        ArrayList<Integer> div_1 = new ArrayList<Integer>();
        ArrayList<Integer> div_2 = new ArrayList<Integer>();
        for (int i = 1; i <= limit; ++i) {
            if (n % i == 0) {
                div_1.add(i);
                int div2 = n / i;
                if (i != div2) {
                    div_2.add(div2);
                }
            }
        }

        for (int i = div_2.size() - 1; i >= 0; --i) {
            div_1.add(div_2.get(i));
        }

        return div_1;
    }

    /**
     * Returns Erathostenes sieve for pN, that is a boolean array indicating the NOT-primes for the range [0...pN].
     * @param pN
     * @return
     */
    public static boolean[] getEratosthenesSieve(int pN) {
        int limit = MathOps.floor(Math.sqrt(pN));
        boolean[] sieve = new boolean[pN + 1];
        /* 0 and 1 are not primes. */
        sieve[0] = true;
        sieve[1] = true;
        /* Remove even numbers > 2. */
        for (int i = 4; i <= pN; i += 2) {
            sieve[i] = true;
        }
        for (int i = 3; i <= limit; i += 2) {
            if (!sieve[i]) {
                /* If i is not true, then it is prime. */
                for (int j = i * i; j <= pN; j += 2 * i) {
                    sieve[j] = true;
                }
            }
        }
        return sieve;
    }

    /**
     * Returns the ordered list of the first n prime numbers, starting at 2.
     * @param n
     * @return
     */
    public static ArrayList<Integer> getFirstPrimes(final int n) {
        final ArrayList<Integer> list = new ArrayList<Integer>(8);
        int p = 2;
        while (list.size() < n) {
            if (isPrime(p)) {
                list.add(p);
            }
            p++;
        }
        return list;
    }

    /**
     * Returns the PGCD between two integers.
     * @param a
     * @param b
     * @return
     */
    public static int getPGCD(final int a, final int b) {
        final ArrayList<Integer> dec_a = getPrimeDecomposition(a);
        final ArrayList<Integer> dec_b = getPrimeDecomposition(b);

        final ArrayList<Integer> commonFactors = new ArrayList<Integer>(4);
        for (int i = 0; i < dec_a.size(); ++i) {
            for (int j = 0; j < dec_b.size(); ++j) {
                if (dec_a.get(i) == dec_b.get(j)) {
                    commonFactors.add(dec_b.remove(j));
                    break;
                }
            }
        }

        int pgcd = 1;
        for (int i = 0; i < commonFactors.size(); ++i) {
            pgcd *= commonFactors.get(i);
        }

        return pgcd;
    }

    /**
     * Returns the PPCM of two integers.
     * @param a
     * @param b
     * @return
     */
    public static int getPPCM(final int a, final int b) {
        final ArrayList<Integer> dec_a = getPrimeDecomposition(a);
        final ArrayList<Integer> dec_b = getPrimeDecomposition(b);

        final ArrayList<Integer> factors = new ArrayList<Integer>(4);
        int j = 0;
        for (int i = 0; i < dec_a.size(); ++i) {

            while (j < dec_b.size() && dec_b.get(j) < dec_a.get(i)) {
                factors.add(dec_b.get(j));
                j++;
            }

            if (j < dec_b.size() && dec_b.get(j) == dec_a.get(i)) {
                j++;
            }

            factors.add(dec_a.get(i));
        }

        for (int i = j; i < dec_b.size(); ++i) {
            factors.add(dec_b.get(i));
        }

        int ppcm = 1;
        for (int i = 0; i < factors.size(); ++i) {
            ppcm *= factors.get(i);
        }

        return ppcm;
    }

    /**
     * Returns the nIdx-st prime (getPrime(1) = 2).
     * @param nIdx
     * @return
     */
    public static int getPrime(int nIdx) {
        int count = 1;
        int candidate = 1;
        while (count < nIdx) {
            candidate += 2;
            if (isPrime(candidate)) {
                count++;
            }
        }
        return candidate;
    }

    /**
     * Returns the prime decomposition of a BigInteger.
     * @param pN
     * @return
     */
    public static ArrayList<BigInteger> getPrimeDecomposition(BigInteger pN) {
        final ArrayList<BigInteger> list = new ArrayList<BigInteger>(4);
        BigInteger two = BigInteger.valueOf(2);

        if (pN.compareTo(two) < 0) {
            throw new IllegalArgumentException("must be greater than one");
        }

        while (pN.mod(two).equals(BigInteger.ZERO)) {
            list.add(two);
            pN = pN.divide(two);
        }

        if (pN.compareTo(BigInteger.ONE) > 0) {
            BigInteger f = BigInteger.valueOf(3);
            while (f.multiply(f).compareTo(pN) <= 0) {
                if (pN.mod(f).equals(BigInteger.ZERO)) {
                    list.add(f);
                    pN = pN.divide(f);
                }
                else {
                    f = f.add(two);
                }
            }
            list.add(pN);
        }

        return list;
    }

    /**
     * Returns the prime decomposition (ordered) of an integer.
     * @param n
     * @return
     */
    public static ArrayList<Integer> getPrimeDecomposition(int n) {
        final ArrayList<Integer> list = new ArrayList<Integer>(4);

        if (n == 1) {
            list.add(1);
        }

        final int limit = (int) (Math.sqrt(n));
        int prime = 2;
        while (n > 1) {
            while (n % prime == 0) {
                n /= prime;
                list.add(prime);
            }
            prime++;
            while (!isPrime(prime)) {
                prime++;
            }
            if (prime > limit) {
                break;
            }
        }

        if (n > 1) {
            list.add(n);
        }

        return list;
    }

    /**
     * Returns the ordered list of prime numbers below n (included).
     * @param n
     * @return
     */
    public static ArrayList<Integer> getPrimesBelow(final int n) {
        final ArrayList<Integer> list = new ArrayList<Integer>(8);

        for (int i = 2; i <= n; ++i) {
            if (isPrime(i)) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Returns true if pN is a prime number.
     * @param pN
     * @return
     */
    public static boolean isPrime(int pN) {
        if (pN == 1) {
            return false;
        }
        else if (pN < 4) {
            return true;
        }
        else if (pN % 2 == 0) {
            return false;
        }
        else if (pN < 9) {
            return true;
        }
        else if (pN % 3 == 0) {
            return false;
        }
        else {
            int r = MathOps.floor(Math.sqrt(pN));
            int f = 5;
            while (f <= r) {
                if (pN % f == 0) {
                    return false;
                }
                else if (pN % (f + 2) == 0) {
                    return false;
                }
                f += 6;
            }
        }
        return true;
    }

    /**
     * Returns true if pN is a prime number.
     * @param pN
     * @return
     */
    public static boolean isPrime(long pN) {
        if (pN == 1L) {
            return false;
        }
        else if (pN < 4L) {
            return true;
        }
        else if (pN % 2 == 0) {
            return false;
        }
        else if (pN < 9L) {
            return true;
        }
        else if (pN % 3 == 0) {
            return false;
        }
        else {
            int r = MathOps.floor(Math.sqrt(pN));
            int f = 5;
            while (f <= r) {
                if (pN % f == 0) {
                    return false;
                }
                else if (pN % (f + 2) == 0) {
                    return false;
                }
                f += 6;
            }
        }
        return true;
    }
}