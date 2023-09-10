package per.study.guava.io;

import com.google.common.base.Preconditions;

public final class Base64 {

    // 0-63
    private static final String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static final char[] CODE_DICT = CODE_STRING.toCharArray();

    private Base64() {

    }

    public static String encode(String plain) {
        Preconditions.checkNotNull(plain);
        StringBuilder result = new StringBuilder();

        String binaryString = toBinary(plain);
        int delta = 6 - binaryString.length() % 6;
        for (int i = 0; i < delta && delta != 6; i++) {
            binaryString += "0";
        }

        for (int index = 0; index < binaryString.length() / 6; index++) {
            int begin = index * 6;
            String encodeString = binaryString.substring(begin, begin + 6);
            char encodeChar = CODE_DICT[Integer.valueOf(encodeString, 2)];
            result.append(encodeChar);
        }

        if (delta != 6) {
            for (int i = 0; i < delta / 2; i++) {
                result.append("=");
            }
        }

        return result.toString();
    }

    private static String toBinary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            String charBin = Integer.toBinaryString(source.charAt(index));
            int detla = 8 - charBin.length();
            for (int d = 0; d < detla; d++) {
                charBin = "0" + charBin;
            }

            binaryResult.append(charBin);
        }
        return binaryResult.toString();
    }

    public static String decode(String encrypt) {
        StringBuilder resultBuilder = new StringBuilder();
        String tmp = encrypt;
        int equalIndex = tmp.indexOf("=");
        if (equalIndex > 0) {
            tmp = tmp.substring(0, equalIndex);
        }
        String base64Binary = toBase64Binary(tmp);
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i * 8;
            String str = base64Binary.substring(begin, begin + 8);
            char c = Character.toChars(Integer.valueOf(str, 2))[0];
            resultBuilder.append(c);
        }
        return resultBuilder.toString();
    }

    private static String toBase64Binary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            // 数组中的下表
            int pos = CODE_STRING.indexOf(source.charAt(index));
            String charBin = Integer.toBinaryString(pos);
            int detla = 6 - charBin.length();
            for (int d = 0; d < detla; d++) {
                charBin = "0" + charBin;
            }
            binaryResult.append(charBin);
        }

        return binaryResult.toString();
    }

}
