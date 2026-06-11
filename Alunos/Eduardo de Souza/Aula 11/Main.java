package attDOO;

import attDOO.controller.AppController;
import attDOO.view.AppView;

public class Main {
    public static void main(String[] args) {

        AppView view = new AppView();

        new AppController(view);
    }
}
