package ro.anghel.ctr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ro.anghel.view.ControllableView;

public abstract class AbstractController<T_Ctr extends Controller<T_Ctr, T_PrevCtr>, T_PrevCtr extends Controller<?, ?>, T_CtrKey, T_BoKey, T_ViewKey>
		implements Controller<T_Ctr, T_PrevCtr> {

	private T_PrevCtr previousController;

	private final Map<T_CtrKey, Controller<?, T_Ctr>> subCtrMap = new HashMap<>();

	private final Map<T_BoKey, Object> boMap = new HashMap<>();

	private final Map<T_ViewKey, ControllableView> viewMap = new HashMap<>();

	protected AbstractController(final T_PrevCtr previousController,
			final ControllableView[] views, final Object[] businessObjects) {
		this.previousController = previousController;
		addViews(views);
		addBusinessObjects(businessObjects);
		addSubControllers();
	}

	protected abstract void addSubControllers();

	protected abstract void addViews(ControllableView[] views);

	protected abstract void addBusinessObjects(Object[] businessObjects);

	@Override
	public Iterable<Controller<?, T_Ctr>> getSubControllers() {
		return subCtrMap.values();
	}

	protected void putSubController(final T_CtrKey key,
			final Controller<?, T_Ctr> subController) {
		subCtrMap.put(key, subController);
	}

	protected void removeSubController(final T_CtrKey key) {
		subCtrMap.remove(key);
	}

	protected void removeAllSubControllers() {
		subCtrMap.clear();
	}

	protected Controller<?, T_Ctr> getSubController(final T_CtrKey key) {
		return subCtrMap.get(key);
	}

	protected void putBusinessObject(final T_BoKey key,
			final Object businessObject) {
		boMap.put(key, businessObject);
	}

	protected void removeBusinessObject(final T_BoKey key) {
		boMap.remove(key);
	}

	protected Object getBusinessObject(final T_BoKey key) {
		return boMap.get(key);
	}

	protected Object[] getBusinessObjects() {
		final Collection<Object> bos = boMap.values();
		return bos.toArray(new Object[bos.size()]);
	}

	protected void removeAllBusinessObjects() {
		boMap.clear();
	}

	@Override
	public void dispose() {
		setVisible(false);
		stopListening();
		disposeSubControllers();
		disposeController();
	}

	protected abstract void disposeController();

	protected void disposeSubControllers() {
		for (final Controller<?, T_Ctr> controller : subCtrMap.values()) {
			controller.dispose();
		}
	}

	@Override
	public Iterable<ControllableView> getViews() {
		return viewMap.values();
	}

	protected void putView(final T_ViewKey key, final ControllableView view) {
		viewMap.put(key, view);
	}

	protected void removeView(final T_ViewKey key) {
		viewMap.remove(key);
	}

	protected ControllableView getView(final T_ViewKey key) {
		return viewMap.get(key);
	}

	protected void removeAllViews() {
		viewMap.clear();
	}

	@Override
	public T_PrevCtr getPreviousController() {
		return previousController;
	}

	protected void setPreviousController(final T_PrevCtr previousController) {
		this.previousController = previousController;
	}

	@Override
	public void init() {
		initBusinessObjects();
		initController();
		initSubcontrollers();
		initViews();
		startListening();
		setVisible(true);
	}

	protected void refresh(final Object[] businessObjects) {
		// dispose();
		stopListening();
		disposeSubControllers();
		disposeController();

		removeAllSubControllers();
		removeAllBusinessObjects();
		addBusinessObjects(businessObjects);
		addSubControllers();
		// init();

		initBusinessObjects();
		initController();
		initSubcontrollers();
		initViews();
		startListening();
	}

	@Override
	public void reinit() {
		dispose();
		init();
	}

	protected abstract void initViews();

	protected abstract void initBusinessObjects();

	protected abstract void initController();

	protected void initSubcontrollers() {
		for (final Controller<?, T_Ctr> controller : subCtrMap.values()) {
			controller.init();
		}
	}

	protected void startListening() {
		for (final ControllableView view : viewMap.values()) {
			if (view != null) {
				view.addListener(this);
			}
		}
	}

	protected void stopListening() {
		for (final ControllableView view : viewMap.values()) {
			if (view != null) {
				view.removeListener(this);
			}
		}
	}

	protected abstract void setVisible(boolean visible);

	protected abstract void updateController();

	@Override
	public void update() {
		updateSubControllers();
		updateController();
	}

	protected void updateSubControllers() {
		for (final Controller<?, T_Ctr> controller : subCtrMap.values()) {
			controller.update();
		}
	}

}
