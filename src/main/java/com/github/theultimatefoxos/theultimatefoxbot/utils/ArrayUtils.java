package com.github.theultimatefoxos.theultimatefoxbot.utils;

public class ArrayUtils {
	public static boolean contains(String[] array, String value) {
		for (String s : array) {
			if (s.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static String[] add(String[] array, String value) {
		String[] newArray = new String[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = value;
		return newArray;
	}

	public static String[] remove(String[] array, String value) {
		String[] newArray = new String[array.length - 1];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (!array[i].equals(value)) {
				newArray[index] = array[i];
				index++;
			}
		}
		return newArray;
	}

	public static String stringify(String[] array, String join) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i]);
			if (i != array.length - 1) {
				builder.append(join);
			}
		}
		return builder.toString();
	}
}
