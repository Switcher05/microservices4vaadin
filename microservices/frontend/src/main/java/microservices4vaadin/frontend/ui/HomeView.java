package microservices4vaadin.frontend.ui;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends VerticalLayout implements View {

    private static final long serialVersionUID = -7001285092564194997L;

    public static final String VIEW_NAME = "";

    private CssLayout dashboardPanels = new CssLayout();

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a view scoped view"));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(dashboardPanels);
        addComponent(layout);

        buildAccountGraph();
    }

    private void buildAccountGraph() {
        class FeederThread extends Thread {
            @Override
            public void run() {
                try {
                    getUI().access(() -> dashboardPanels.addComponent(new Label("Hallo")));
                    Thread.sleep(10000);
                    getUI().access(() -> dashboardPanels.addComponent(new Label("Du")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        new FeederThread().start();
    }

}
