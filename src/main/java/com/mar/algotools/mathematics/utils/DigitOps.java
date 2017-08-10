package com.mar.algotools.mathematics.utils;

import java.math.BigInteger;
import java.util.ArrayList;

public class DigitOps {

    /**
     * Returns true if pDigits1 contains pDigits2 in the same order, but not necessarily consecutive.
     * @param pDigits1
     * @param pDigits2
     * @return
     */
    public static boolean contains(ArrayList<Integer> pDigits1, ArrayList<Integer> pDigits2) {
        if (pDigits2.size() > pDigits1.size()) {
            return false;
        }
        int startIdx = 0;
        boolean contained = true;
        for (int i = 0; i < pDigits2.size(); ++i) {
            boolean found = false;
            for (int j = startIdx; j < pDigits1.size(); ++j) {
                if (pDigits1.get(j) == pDigits2.get(i)) {
                    found = true;
                    startIdx = j + 1;
                    break;
                }
            }
            if (found == false) {
                contained = false;
                break;
            }
        }
        return contained;
    }

    /**
     * Returns the list of digits in pN. The unit digit is the first one in the list.
     * @param pN
     * @return
     */
    public static ArrayList<Integer> getDigits(int pN) {
        return getDigitsInBase(pN, 10);
    }

    /**
     * Returns the list of digits in pN. The unit digit is the first one in the list.
     * @param pN
     * @return
     */
    public static ArrayList<Integer> getDigits(long pN) {
        return getDigitsInBase(pN, 10);
    }

    /**
     * Returns the list of digits in pN in the specified base. The unit digit is the first one in the list.
     * @param pN
     * @param pRadix
     * @return
     */
    public static ArrayList<Integer> getDigitsInBase(int pN, int pRadix) {
        if (pN < 0) {
            pN *= -1;
        }
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (pN > 0) {
            digits.add(pN % pRadix);
            pN = pN / pRadix;
        }
        return digits;
    }

    /**
     * Returns the list of digits in pN in the specified base. The unit digit is the first one in the list.
     * @param pN
     * @param pRadix
     * @return
     */
    public static ArrayList<Integer> getDigitsInBase(long pN, int pRadix) {
        if (pN < 0) {
            pN *= -1;
        }
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (pN > 0) {
            digits.add((int) (pN % pRadix));
            pN = pN / pRadix;
        }
        return digits;
    }

    /**
     * Returns the number of digits in pN.
     * @param pN
     * @return
     */
    public static int getNbDigits(int pN) {
        return getDigits(pN).size();
    }

    /**
     * Returns the number constructed from the specified list of digits. The unit digit is the first one in the list.
     * @param pDigits
     * @return
     */
    public static int getNumberFromDigits(ArrayList<Integer> pDigits) {
        int coef = 1;
        int n = 0;
        for (int i = 0; i < pDigits.size(); ++i) {
            n += pDigits.get(i) * coef;
            coef *= 10;
        }
        return n;
    }

    /**
     * Returns the number constructed from the specified list of digits. The unit digit is the first one in the list.
     * @param pDigits
     * @return
     */
    public static long getNumberFromDigitsAsLong(ArrayList<Integer> pDigits) {
        long coef = 1;
        long n = 0;
        for (int i = 0; i < pDigits.size(); ++i) {
            n += pDigits.get(i) * coef;
            coef *= 10;
        }
        return n;
    }

    /**
     * Returns the sum of the digits of pN in base 10. E.g. getSumOfDigits(123) = 1+2+3 = 6.
     * @param pN
     * @return
     */
    public static int getSumOfDigits(BigInteger pN) {
        String s = pN.toString();
        int sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            sum += Integer.parseInt("" + s.charAt(i));
        }
        return sum;
    }

    /**
     * Returns the sum of the digits of pN in base 10. E.g. getSumOfDigits(123) = 1+2+3 = 6.
     * @param pN
     * @return
     */
    public static int getSumOfDigits(int pN) {
        if (pN < 0) {
            pN *= -1;
        }
        int sum = 0;
        while (pN > 0) {
            sum = sum + pN % 10;
            pN = pN / 10;
        }
        return sum;
    }

    /**
     * Returns the application of getSumOfDigits() recursively until the result is lower than 10.
     * @param pN
     * @return
     */
    public static int getSumOfDigitsLT10(int pN) {
        while (pN > 10) {
            pN = getSumOfDigits(pN);
        }
        return pN;
    }

    /**
     * Returns true if the two numbers have the same digits, e.g. 1228 and 8212.
     * @param pN1
     * @param pN2
     * @return
     */
    public static boolean hasSameDigits(long pN1, long pN2) {
        ArrayList<Integer> d1 = getDigits(pN1);
        ArrayList<Integer> d2 = getDigits(pN2);

        if (d1.size() != d2.size()) {
            return false;
        }

        for (int i = 0; i < d1.size(); ++i) {
            int v = d1.get(i);
            for (int j = 0; j < d2.size(); ++j) {
                if (d2.get(j) == v) {
                    d2.remove(j);
                    break;
                }
            }
        }

        if (d2.size() > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Returns true if the number is a palindrome (e.g. 9119).
     * @param pN
     * @return
     */
    public static boolean isPalindrome(int pN) {
        return isPalindromeInBase(pN, 10);
    }

    /**
     * Returns true if the number is a palindrome in the specified base.
     * @param pN
     * @param pRadix
     * @return
     */
    public static boolean isPalindromeInBase(int pN, int pRadix) {
        ArrayList<Integer> digits = getDigitsInBase(pN, pRadix);
        boolean isPalindrome = true;
        for (int i = 0; i < digits.size() / 2; ++i) {
            if (digits.get(i).intValue() != digits.get(digits.size() - 1 - i).intValue()) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    /**
     * Returns true if pN is (pA,pB)-pandigital, meaning that it contains all numbers from pA to pB once.
     * @param pN
     * @param pA
     * @param pB
     * @return
     */
    public static boolean isPandigital(int pN, int pA, int pB) {
        ArrayList<Integer> digits = getDigits(pN);
        if (digits.size() != pB - pA + 1) {
            return false;
        }
        boolean isPandigital = true;
        boolean[] hasNumber = new boolean[pB - pA + 1];
        for (int i = 0; i < digits.size(); ++i) {
            int idx = digits.get(i) - pA;
            if (idx >= 0 && idx <= pB - pA) {
                hasNumber[idx] = true;
            }
        }
        for (int i = 0; i <= pB - pA; ++i) {
            if (hasNumber[i] == false) {
                isPandigital = false;
                break;
            }
        }
        return isPandigital;
    }

}