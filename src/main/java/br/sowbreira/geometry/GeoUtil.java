package br.sowbreira.geometry;

import java.awt.Graphics;
import java.awt.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author  Paulo Sobreira
 * 13:47 23/4/2004
 *
 */
public class GeoUtil {
    public static int GRAVITY = 10;
    public static int LEFT_TO_RIGHT = 0;
    public static int RIGHT_TO_LEFT = 1;

    public static List drawParabola(int velocity, int angle,
        Point initialPostion, int orientation, Graphics graphics) {
        List list = new ArrayList();
        double vx;
        double vy;
        double newx = 0;
        double newy;
        double oldx;
        double oldy;
        vx = velocity * Math.cos(Math.toRadians(angle));
        vy = velocity * Math.sin(Math.toRadians(angle));
        oldx = initialPostion.x;
        oldy = initialPostion.y;

        for (int i = 0; i < velocity; i++) {
            if (orientation == LEFT_TO_RIGHT) {
                newx = initialPostion.x + (vx * i);
            } else if (orientation == RIGHT_TO_LEFT) {
                newx = initialPostion.x - (vx * i);
            }

            newy = initialPostion.y - (vy * i) +
                ((GRAVITY * Math.pow(i, 2)) / 2);
            list.addAll(drawBresenhamLine((int) oldx, (int) oldy, (int) newx,
                    (int) newy));
            oldx = newx;
            oldy = newy;
        }

        return list;
    }

    public static List drawBresenhamLine(Point p1, Point p2) {
        return drawBresenhamLine(p1.x, p1.y, p2.x, p2.y);

        //return myDrawLine(p1.x,p1.y,p2.x,p2.y);
    }

    public static List drawBresenhamLine(int x0, int y0, int x1, int y1) {
        LinkedList list = new LinkedList();
        int dy = y1 - y0;
        int dx = x1 - x0;
        int stepx;
        int stepy;

        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        } else {
            stepy = 1;
        }

        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }

        // dy is now 2*dy
        dy <<= 1;

        // dx is now 2*dx
        dx <<= 1;

        list.add(new Point(x0, y0));

        if (dx > dy) {
            //	same as 2*dy - dx
            int fraction = dy - (dx >> 1);

            while (x0 != x1) {
                if (fraction >= 0) {
                    y0 += stepy;

                    // same as fraction -= 2*dx
                    fraction -= dx;
                }

                x0 += stepx;

                // same as fraction -= 2*dy
                fraction += dy;
                list.add(new Point(x0, y0));
            }
        } else {
            int fraction = dx - (dy >> 1);

            while (y0 != y1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }

                y0 += stepy;
                fraction += dx;
                list.add(new Point(x0, y0));
            }
        }

        return list;
    }



    public static List myDrawLine(int x1, int y1, int x2, int y2) {
        LinkedList list = new LinkedList();
        int temp;
        int dy_neg = 1;
        int dx_neg = 1;
        int switch_x_y = 0;
        int neg_slope = 0;
        int tempx;
        int tempy;
        int dx = x2 - x1;

        if (dx == 0) {
            if (y1 > y2) {
                for (int n = y2; n <= y1; n++) {
                    list.add(new Point(x1, n));
                }

                return list;
            } else {
                for (int n = y1; n <= y2; n++) {
                    list.add(new Point(x1, n));
                }

                return list;
            }
        }

        int dy = y2 - y1;

        if (dy == 0) {
            if (x1 > x2) {
                for (int n = x2; n <= x1; n++) {
                    list.add(new Point(n, y1));
                }

                return list;
            } else {
                for (int n = x1; n <= x2; n++) {
                    list.add(new Point(n, y1));
                }

                return list;
            }
        }

        float m = (float) dy / dx;

        if ((m > 1) || (m < -1)) {
            temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
            dx = x2 - x1;
            dy = y2 - y1;
            m = (float) dy / dx;
            switch_x_y = 1;
        }

        if (x1 > x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
            dx = x2 - x1;
            dy = y2 - y1;
            m = (float) dy / dx;
        }

        if (m < 0) {
            if (dy < 0) {
                dy_neg = -1;
                dx_neg = 1;
            } else {
                dy_neg = 1;
                dx_neg = -1;
            }

            neg_slope = 1;
        }

        int d = (2 * (dy * dy_neg)) - (dx * dx_neg);
        int incrH = 2 * dy * dy_neg;
        int incrHV = 2 * ((dy * dy_neg) - (dx * dx_neg));
        int x = x1;
        int y = y1;
        tempx = x;
        tempy = y;

        if (switch_x_y == 1) {
            temp = x;
            x = y;
            y = temp;
        }

        list.add(new Point(x, y));
        x = tempx;
        y = tempy;

        while (x < x2) {
            if (d <= 0) {
                x++;
                d += incrH;
            } else {
                d += incrHV;
                x++;

                if (neg_slope == 0) {
                    y++;
                } else {
                    y--;
                }
            }

            tempx = x;
            tempy = y;

            if (switch_x_y == 1) {
                temp = x;
                x = y;
                y = temp;
            }

            list.add(new Point(x, y));
            x = tempx;
            y = tempy;
        }

        return list;
    }
}
