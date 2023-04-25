package br.sowbreira.jgorillaz;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * @author Paulo Created on 24/12/2004
 */
public class RasterManager {
	public static final int OUT = 0;
	public static final int P1 = 1;
	public static final int P2 = 2;
	public static final int TERRAIN = 3;
	public static final int RUNNIG = 4;
	private Raster raster;
	private int[] argbArray;
	private BufferedImage image;

	public int moveAt(Point point) {
		return moveAt(point.x, point.y);
	}

	public int moveAt(int x, int y) {
		if ((x < 0) || (x >= image.getWidth()) || (y >= image.getHeight())) {
			return OUT;
		}

		if ((y < 0)) {
			return RUNNIG;
		}
		argbArray = new int[4];
		argbArray = raster.getPixel(x, y, argbArray);

		Color c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		int comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];
		try {
			argbArray = raster.getPixel(x + 1, y, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x - 1, y, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x - 1, y - 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x - 1, y + 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x + 1, y - 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x + 1, y + 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x, y - 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		argbArray = new int[4];

		try {
			argbArray = raster.getPixel(x, y + 1, argbArray);
		} catch (Exception e) {
			return OUT;
		}

		c = new Color(argbArray[0], argbArray[1], argbArray[2], argbArray[3]);

		comparePixel = comparePixel(c);
		if (comparePixel != 0) {
			return comparePixel;
		}
		return RUNNIG;
	}

	private int comparePixel(Color c) {
		if (c.equals(GorillazPanel.P1COLOR)) {
			return P1;
		} else if (c.equals(GorillazPanel.P2COLOR)) {
			return P2;
		} else if (c.equals(GorillazPanel.GORUND)) {
			return TERRAIN;
		}
		return 0;
	}

	/**
	 * @param image The image to set.
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
		raster = image.getData();
	}

	public Raster getRaster() {
		return raster;
	}

	public BufferedImage getImage() {
		return image;
	}
}
