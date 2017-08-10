package com.mar.algotools.crypto;

import com.mar.algotools.sorting.Sorter;

public class CryptoUtils {

    public static final double IC_ENGLISH = 1.73;

    public static final double[] FREQ_ENGLISH = { 0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
            0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327,
            0.09056, 0.02758, 0.00978, 0.02361, 0.00150, 0.01974, 0.00074 };

    /**
     * Returns the chi square statistic for the specified text, using the English frequencies.
     * @param pText
     * @return
     */
    public static double getChiSquare(String pText) {
        int length = pText.length();
        int[] letterCount = getLetterCount(pText);

        double chiSquare = 0.0;
        for (int i = 0; i < 26; ++i) {
            double e = FREQ_ENGLISH[i] * length;
            chiSquare += Math.pow(letterCount[i] - e, 2.0) / e;
        }

        return chiSquare;
    }

    /**
     * Returns the indexes sorted from the closest to English IC to the farthest.
     * @param pIc
     * @return
     */
    public static int[] getIdxOfMostProbableIC(double[] pIc) {
        double[] dist = new double[pIc.length];
        for (int i = 0; i < pIc.length; ++i) {
            dist[i] = Math.abs(pIc[i] - IC_ENGLISH);
        }
        return Sorter.getSortedIdx(dist, Sorter.ASCEND);
    }

    /**
     * Returns the index of coincidence for the specified string. Only alphabetical character are considered.
     * @param pString
     * @return
     */
    public static double getIndexOfCoincidence(String pString) {
        /* Evaluate lower case letters frequencies. */
        int[] letterCount = getLetterCount(pString);
        int length = pString.length();

        /* Compute index of coincidence. */
        double ic = 0.0;
        for (int i = 0; i < letterCount.length; ++i) {
            ic += (letterCount[i] * (letterCount[i] - 1)) / (double) (length * (length - 1));
        }
        ic *= 26;

        return ic;
    }

    /**
     * Returns the alphabetical letter count.
     * @param pText
     * @return
     */
    public static int[] getLetterCount(String pText) {
        int[] letterCount = new int[26];
        String s = pText.toLowerCase();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            int idx = s.charAt(i) - 'a';
            if (idx >= 0 && idx < letterCount.length) {
                letterCount[s.charAt(i) - 'a']++;
            }
        }
        return letterCount;
    }

    /**
     * Returns true if pChar is an alphabetical character (a-z or A-Z).
     * @param pChar
     * @return
     */
    public static boolean isAlphabeticalCharacter(char pChar) {
        return (pChar >= 'A' && pChar <= 'Z') || (pChar >= 'a' && pChar <= 'z');
    }

    /**
     * Returns the string with a specified offset. Only alphabetical characters are considered, and they stays in the
     * lower case range.
     * @param pText
     * @param pOffset
     * @return
     */
    public static String offsetCircular(String pText, int pOffset) {
        pText = pText.toLowerCase();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < pText.length(); ++i) {
            char c = pText.charAt(i);
            if (!isAlphabeticalCharacter(c)) {
                out.append(c);
            }
            else {
                int ci = c - 97 + pOffset;
                while (ci >= 26) {
                    ci -= 26;
                }
                while (ci < 0) {
                    ci += 26;
                }
                char newc = (char) (ci + 97);
                out.append(newc);
            }
        }
        return out.toString();
    }

}