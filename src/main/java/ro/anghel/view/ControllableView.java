package ro.anghel.view;

import ro.anghel.ctr.Controller;

public interface ControllableView {

	void addListener(Controller<?, ?> aController);

	void removeListener(Controller<?, ?> aController);

}
