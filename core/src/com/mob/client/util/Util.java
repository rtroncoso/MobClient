/*******************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * General math methods
 * TODO : 
 * 	- Split into a Math class and give everything it's right place
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.util;


public class Util {

	public static boolean asciiValidos(String str) {
		// Function AsciiValidos(ByVal cad As String) As Boolean
		byte[] bytes = str.toLowerCase().getBytes();
		for (byte element : bytes) {
			if ((element < 97 || element > 122) && element != 255
					&& element != 32) {
				return false;
			}
		}
		return true;
	}

	public static short leShort(short n) {
		return (short) (((n & 0xff) << 8) | (((n & 0xff00) >> 8) & 0xff));
	}

	public static int leInt(int n) {
		return (((n & 0xff) << 24) | ((n & 0xff00) << 8) | ((n & 0xff0000) >> 8) | ((n & 0xff000000) >> 24));
	}
	
	public static int leMix(short j, short k) {
		int a = j & 0xffff;
		int b = k & 0xffff0000;
		return (int) ((b << 16) | a) >> 31;
	}

	public static double distance(int x1, int y1, int x2, int y2) {
		// Encuentra la distancia entre dos puntos
		return Math.sqrt(((y1 - y2) * (y1 - y2)) + ((x1 - x2) * (x1 - x2)));
	}

	public static int porcentaje(long total, long porc) {
		return (int) Math.round((total * porc) / 100.0);
	}

	public static int randomInt(int min, int max) {
		int valor = (int) ((Math.random() * (max - min + 1)) + min);
		return (valor < min) ? min : valor;
	}

	public static short Min(short a, short b) {
		return a < b ? a : b;
	}

	public static int Min(int a, int b) {
		return a < b ? a : b;
	}

	public static long Min(long a, long b) {
		return a < b ? a : b;
	}

	public static double Min(double a, double b) {
		return a < b ? a : b;
	}

	public static short Max(short a, short b) {
		return a > b ? a : b;
	}

	public static int Max(int a, int b) {
		return a > b ? a : b;
	}

	public static long Max(long a, long b) {
		return a > b ? a : b;
	}

	public static double Max(double a, double b) {
		return a > b ? a : b;
	}
}
