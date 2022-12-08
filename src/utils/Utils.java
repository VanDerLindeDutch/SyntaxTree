package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {
    public static List<String> deleteSpaces(String string) {
        char[] chars = string.toCharArray();
        Character[] charactersWithoutSpaces = IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .filter(x -> x != ' ').
                toArray(Character[]::new);
        StringBuilder buf = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (Character charactersWithoutSpace : charactersWithoutSpaces) {
            if (charactersWithoutSpace >= 48 && charactersWithoutSpace <= 57) {
                buf.append(charactersWithoutSpace.charValue());
            } else {
                if (!buf.isEmpty()) {
                    list.add(buf.toString());
                    buf = new StringBuilder();
                }
                list.add(charactersWithoutSpace.toString());
            }
        }
        if (!buf.isEmpty()) {
            list.add(buf.toString());
        }
        System.out.println(list);
        return list;

    }
}
