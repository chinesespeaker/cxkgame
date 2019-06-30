package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ikun extends GameObject {


    boolean left,up,right,down;

    boolean live = true;

    public void drawSelf(Graphics g){
        if(live){
            g.drawImage(img,(int)x,(int)y,null);
            //x++;
            if(left){
                x -= speed;
            }
            if(right){
                x += speed;
            }
            if(up){
                y -= speed; //y=y-speed;
            }
            if(down){
                y += speed;
            }

        }

    }

    public ikun(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 4;
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
    }

    //按下某个键，键增加的方向
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:  //英文冒号
            left = true;
            break;
            case KeyEvent.VK_UP:
            up = true;
            break;
            case KeyEvent.VK_RIGHT:
            right = true;
            break;
            case KeyEvent.VK_DOWN:
            down = true;
            break;
        }

    }

    //按下某个键，键取消的方向
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }

    }
}
