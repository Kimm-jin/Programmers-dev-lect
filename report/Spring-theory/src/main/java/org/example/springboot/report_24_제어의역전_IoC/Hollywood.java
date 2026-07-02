package org.example.springboot.report_24_제어의역전_IoC;

import java.util.ArrayList;
import java.util.List;

public class Hollywood {
    interface ClickListener{
        void onClick();
    }

    class Button{

        private List<ClickListener> listeners = new ArrayList<>();
        void setListener(ClickListener listener){listeners.add(listener);}
        void press(){
            System.out.println("press");
            for(ClickListener l : listeners) l.onClick();
        }
    }

    class LikeAction1 implements ClickListener{

        @Override
        public void onClick() {
            System.out.println("onClick()_1");
        }
    }
    class LikeAction2 implements ClickListener{

        @Override
        public void onClick() {
            System.out.println("onClick()_2");
        }
    }
    class LikeAction3 implements ClickListener{

        @Override
        public void onClick() {
            System.out.println("onClick()_3");
        }
    }
}

