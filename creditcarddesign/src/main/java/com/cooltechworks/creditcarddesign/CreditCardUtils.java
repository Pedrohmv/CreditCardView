package com.cooltechworks.creditcarddesign;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Harish on 03/01/16.
 */
public class CreditCardUtils {
    public enum CardType {
        UNKNOWN_CARD, AMEX_CARD, MASTER_CARD, VISA_CARD, DINERS_CARD, ELO_CARD, HIPERCARD,
        BANESECARD, FORTBRASIL, CREDISHOP, SOROCRED, ASSOMISE
    }

    private static final String PATTERN_AMEX = "^3[47][0-9]{4}$";
    private static final String PATTERN_VISA = "^4[0-9]{5}";
    private static final String PATTERN_MASTER = "^5[1-5][0-9]{4}|^2(?:2(?:2[1-9]|[3-9]\\d)|[3-6]\\d\\d|7(?:[01]\\d|20))[0-9]{2}$";
    private static final String PATTERN_HIPERCARD = "^606282|(637\\d{3})|(3841\\d{2})$";
    private static final String PATTERN_BANESECARD = "^6361[0-9]{2}|^6374[0-9]{2}";
    private static final String PATTERN_FORTBRASIL = "^628167";
    private static final String PATTERN_CREDISHOP = "^6031[0-9]{2}";
    private static final String PATTERN_SOROCRED = "^627892|^606014|^636414|^9555[0-9]{2}";
    private static final String PATTERN_ASSOMISE = "^639595|^608732";
    private static final String PATTERN_DINERS = "^3(?:0[0-5]|[6][0-9]|[8]([0-3]|[5-9]))[0-9]";
    private static final String PATTERN_ELO = "^401178|^401179|^431274|^438935|^451416|^457393|^457631|^457632|^504175|" +
            "^627780|^636297|^636368|^(506699|5067[0-6]\\d|50677[0-8])|^(50900\\d|5090[1-9]\\d|509[1-9]\\d{2})" +
            "|^65003[1-3]|^(65003[5-9]|65004\\d|65005[0-1])|^(65040[5-9]|6504[1-3]\\d)|^(65048[5-9]|65049" +
            "\\d|6505[0-2]\\d|65053[0-8])|^(65054[1-9]|6505[5-8]\\d|65059[0-8])|^(65070\\d|65071[0-8])" +
            "|^65072[0-7]|^(65090[1-9]|65091\\d|6509([2-6]\\d|7[0-8]))|^(65165[2-9]|6516[6-7]\\d)|^(65500\\d" +
            "|65501\\d)|^(65502[1-9]|6550[3-4]\\d|65505[0-8])";

    public static final int MAX_LENGTH_CARD_NUMBER = 16;
    public static final int MAX_LENGTH_CARD_NUMBER_AMEX = 15;

    public static final String CARD_NUMBER_FORMAT = "XXXX XXXX XXXX XXXX";
    public static final String CARD_NUMBER_FORMAT_AMEX = "XXXX XXXXXX XXXXX";
    public static final String CARD_NUMBER_FORMAT_ASSOMISE = "XXXXXX XXXX XXXX XXXX";
    public static final String CARD_NUMBER_FORMAT_FORTBRASIL = "XXXXXX XXXXXX XXXX";

    public static final String EXTRA_CARD_NUMBER = "card_number";
    public static final String EXTRA_CARD_CVV = "card_cvv";
    public static final String EXTRA_CARD_EXPIRY = "card_expiry";
    public static final String EXTRA_CARD_HOLDER_NAME = "card_holder_name";
    public static final String EXTRA_CARD_SHOW_CARD_SIDE = "card_side";
    public static final String EXTRA_VALIDATE_EXPIRY_DATE = "expiry_date";
    public static final String EXTRA_ENTRY_START_PAGE = "start_page";

    public static final int CARD_SIDE_FRONT = 1,CARD_SIDE_BACK=0;

    public static final int CARD_NUMBER_PAGE = 0, CARD_EXPIRY_PAGE = 1;
    public static final int CARD_CVV_PAGE = 2, CARD_NAME_PAGE = 3;

    public static final String SPACE_SEPARATOR = " ";
    public static final String SLASH_SEPARATOR = "/";
    public static final char CHAR_X = 'X';

    public static String handleCardNumber(String inputCardNumber) {

        return handleCardNumber(inputCardNumber, SPACE_SEPARATOR);
    }

    public static CardType selectCardType(String cardNumber) {
        cardNumber = cardNumber.replace(CreditCardUtils.SPACE_SEPARATOR, "");
        if (cardNumber.length() >= 6) {
            cardNumber = cardNumber.substring(0, 6);
            Pattern pCardType = Pattern.compile(PATTERN_ELO);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.ELO_CARD;
            pCardType = Pattern.compile(PATTERN_BANESECARD);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.BANESECARD;
            pCardType = Pattern.compile(PATTERN_ASSOMISE);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.ASSOMISE;
            pCardType = Pattern.compile(PATTERN_HIPERCARD);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.HIPERCARD;
            pCardType = Pattern.compile(PATTERN_BANESECARD);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.BANESECARD;
            pCardType = Pattern.compile(PATTERN_FORTBRASIL);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.FORTBRASIL;
            pCardType = Pattern.compile(PATTERN_CREDISHOP);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.CREDISHOP;
            pCardType = Pattern.compile(PATTERN_SOROCRED);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.SOROCRED;
            pCardType = Pattern.compile(PATTERN_VISA);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.VISA_CARD;
            pCardType = Pattern.compile(PATTERN_MASTER);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.MASTER_CARD;
            pCardType = Pattern.compile(PATTERN_AMEX);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.AMEX_CARD;
            pCardType = Pattern.compile(PATTERN_DINERS);
            if (pCardType.matcher(cardNumber).matches())
                return CardType.DINERS_CARD;
        }
        return CardType.UNKNOWN_CARD;
    }

    public static int selectCardLength(CardType cardType) {
        return cardType == CardType.AMEX_CARD ? MAX_LENGTH_CARD_NUMBER_AMEX : MAX_LENGTH_CARD_NUMBER;
    }

    public static String handleCardNumber(String inputCardNumber, String seperator) {
        String unformattedText = inputCardNumber.replace(seperator, "");
        CardType cardType = selectCardType(inputCardNumber);
        String format = (cardType == CardType.AMEX_CARD) ? CARD_NUMBER_FORMAT_AMEX : CARD_NUMBER_FORMAT;
        StringBuilder sbFormattedNumber = new StringBuilder();
        for(int iIdx = 0, jIdx = 0; (iIdx < format.length()) && (unformattedText.length() > jIdx); iIdx++) {
            if(format.charAt(iIdx) == CHAR_X)
                sbFormattedNumber.append(unformattedText.charAt(jIdx++));
            else
                sbFormattedNumber.append(format.charAt(iIdx));
        }

        return sbFormattedNumber.toString();
    }

    public static String formatCardNumber(String inputCardNumber, String separator) {
        String unformattedText = inputCardNumber.replace(separator, "");
        CardType cardType = selectCardType(inputCardNumber);
        String format = CARD_NUMBER_FORMAT;
        if (cardType == CardType.AMEX_CARD)
            format = CARD_NUMBER_FORMAT_AMEX;
        else if (cardType == CardType.ASSOMISE)
            format = CARD_NUMBER_FORMAT_ASSOMISE;
        else if (cardType == CardType.FORTBRASIL)
            format = CARD_NUMBER_FORMAT_FORTBRASIL;
        StringBuilder sbFormattedNumber = new StringBuilder();
        for(int iIdx = 0, jIdx = 0; iIdx < format.length(); iIdx++) {
            if((format.charAt(iIdx) == CHAR_X) && (unformattedText.length() > jIdx))
                sbFormattedNumber.append(unformattedText.charAt(jIdx++));
            else
                sbFormattedNumber.append(format.charAt(iIdx));
        }

        return sbFormattedNumber.toString().replace(SPACE_SEPARATOR, SPACE_SEPARATOR + SPACE_SEPARATOR);
    }

    public static String handleExpiration(String month, String year) {

        return handleExpiration(month+year);
    }


    public static String handleExpiration(@NonNull String dateYear) {

        String expiryString = dateYear.replace(SLASH_SEPARATOR, "");

        String text;
        if(expiryString.length() >= 2) {
            String mm = expiryString.substring(0, 2);
            String yy;
            text = mm;

            try {
                if (Integer.parseInt(mm) > 12) {
                    mm = "12"; // Cannot be more than 12.
                }
            }
            catch (Exception e) {
                mm = "01";
            }

            if(expiryString.length() >=4) {
                yy = expiryString.substring(2,4);

                try{
                    Integer.parseInt(yy);
                }catch (Exception e) {

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    yy = String.valueOf(year).substring(2);
                }

                text = mm + SLASH_SEPARATOR + yy;

            }
            else if(expiryString.length() > 2){
                yy = expiryString.substring(2);
                text = mm + SLASH_SEPARATOR + yy;
            }
        }
        else {
            text = expiryString;
        }

        return text;
    }
}
