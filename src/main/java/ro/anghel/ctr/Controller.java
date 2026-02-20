package ro.anghel.ctr;

import ro.anghel.view.ControllableView;

public interface Controller<T_Ctr extends Controller<T_Ctr, T_PrevCtr>, T_PrevCtr extends Controller<?, ?>> {
	Iterable<Controller<?, T_Ctr>> getSubControllers();

	Iterable<ControllableView> getViews();

	void init();

	void reinit();

	void update();

	void dispose();

	T_PrevCtr getPreviousController();
}
