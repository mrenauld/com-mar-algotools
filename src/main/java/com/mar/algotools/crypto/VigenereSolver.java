package com.mar.algotools.crypto;

import java.util.ArrayList;

import com.mar.algotools.sorting.Sorter;

/**
 * See http://practicalcryptography.com/cryptanalysis/stochastic-searching/cryptanalysis-vigenere-cipher/
 * @author mrenauld
 */
public class VigenereSolver {

    private String cipherText;

    private String plainText;

    private String key;

    private int maxSequenceLength = 15;

    public VigenereSolver() {

    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String pPlainText) {
        plainText = pPlainText;
    }

    public void solve(String pText) {
        cipherText = pText;

        /* Preprocess. */
        String lowerCaseText = pText.toLowerCase();

        /* Find sequence length. */
        double[] ic = new double[maxSequenceLength];
        for (int s = 1; s <= maxSequenceLength; ++s) {
            ArrayList<StringBuilder> icStringList = new ArrayList<StringBuilder>(s);
            for (int i = 0; i < s; ++i) {
                icStringList.add(new StringBuilder());
            }
            int cpt = 0;
            for (int i = 0; i < lowerCaseText.length(); ++i) {
                char c = lowerCaseText.charAt(i);
                if (!CryptoUtils.isAlphabeticalCharacter(c)) {
                    continue;
                }

                StringBuilder sb = icStringList.get(cpt % s);
                sb.append(c);
                cpt++;
            }
            double icMean = 0.0;
            for (int i = 0; i < s; ++i) {
                icMean += CryptoUtils.getIndexOfCoincidence(icStringList.get(i).toString());
            }
            icMean /= s;
            ic[s - 1] = icMean;
        }

        int[] idxList = CryptoUtils.getIdxOfMostProbableIC(ic);
        System.out.print("Most probable lengths: ");
        for (int i = 0; i < idxList.length; ++i) {
            System.out.print((idxList[i] + 1) + " ");
        }
        System.out.println();

        /* Evaluate the key. */
        int keyLength = idxList[0] + 1;
        ArrayList<StringBuilder> icStringList = new ArrayList<StringBuilder>(keyLength);
        for (int i = 0; i < keyLength; ++i) {
            icStringList.add(new StringBuilder());
        }
        int cpt = 0;
        for (int i = 0; i < lowerCaseText.length(); ++i) {
            char c = lowerCaseText.charAt(i);
            if (!CryptoUtils.isAlphabeticalCharacter(c)) {
                continue;
            }

            StringBuilder sb = icStringList.get(cpt % keyLength);
            sb.append(c);
            cpt++;
        }

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; ++i) {
            double[] chiSquare = new double[26];
            for (int c = 0; c < 26; ++c) {
                String offsetText = CryptoUtils.offsetCircular(icStringList.get(i).toString(), -c);
                chiSquare[c] = CryptoUtils.getChiSquare(offsetText);
            }
            int[] sort = Sorter.getSortedIdx(chiSquare, Sorter.ASCEND);
            char kChar = (char) (sort[0] + 'a');
            key.append(kChar);
        }

        System.out.println("Key = " + key);

        solve(pText, key.toString());
    }

    public void solve(String pText, String pKey) {
        cipherText = pText;
        String lowerCase = pText.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int cpt = 0;
        for (int i = 0; i < pText.length(); ++i) {
            char c = lowerCase.charAt(i);
            if (!CryptoUtils.isAlphabeticalCharacter(c)) {
                sb.append(c);
            }
            else {
                c -= pKey.charAt(cpt % pKey.length()) - 'a';
                while (c > 'z') {
                    c -= 26;
                }
                while (c < 'a') {
                    c += 26;
                }

                if (pText.charAt(i) >= 'A' && pText.charAt(i) <= 'Z') {
                    c -= 32;
                }

                sb.append(c);
                cpt++;
            }
        }

        System.out.println("Solution : " + sb.toString().substring(0, 200));
        setPlainText(sb.toString());
    }

    private boolean isLetter(char pC) {
        return pC >= 97 && pC <= 122;
    }
}