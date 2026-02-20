package ro.anghel.mz.ctr;

import javax.swing.JFrame;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.ui.DlgPlayerDetails;
import ro.anghel.mz.ui.PnlPlayerDetails;
import ro.anghel.view.ControllableView;

public class CtrPlayerDetails<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrPlayerDetails<T_PrevCtr>, T_PrevCtr, CtrPlayerDetails.CtrKey, CtrPlayerDetails.BoKey, CtrPlayerDetails.ViewKey> {

	public enum CtrKey {
		PLAYER_INFO
	}

	public enum BoKey {
		PLAYER
	}

	public enum ViewKey {
		DLG_PLAYER_DETAILS
	}

	public CtrPlayerDetails(final T_PrevCtr aPreviousController,
			final JFrame aParentWindow, final Player aPlayer) {
		super(aPreviousController,
				new ControllableView[] { new DlgPlayerDetails(aParentWindow) },
				new Object[] { aPlayer });
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.PLAYER, aBusinessObjects[0]);
	}

	@Override
	protected void addSubControllers() {
		final PnlPlayerDetails pnlPlayerDetails = getDlgPlayerDetails()
				.getPnlPlayerDetails();
		final Player player = getPlayer();
		putSubController(CtrKey.PLAYER_INFO,
				new CtrPlayerInfo<>(this,
						pnlPlayerDetails, player));
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.DLG_PLAYER_DETAILS, aViews[0]);
	}

	public Player getPlayer() {
		return (Player) getBusinessObject(BoKey.PLAYER);
	}

	protected DlgPlayerDetails getDlgPlayerDetails() {
		return (DlgPlayerDetails) getView(ViewKey.DLG_PLAYER_DETAILS);
	}

	@Override
	protected void disposeController() {
		getDlgPlayerDetails().dispose();
	}

	@Override
	protected void initViews() {
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
		final DlgPlayerDetails dlg = getDlgPlayerDetails();
		if (aVisible) {
			dlg.pack();
			dlg.setLocationRelativeTo(dlg.getParent());
		}
		dlg.setVisible(aVisible);
	}

	@Override
	protected void updateController() {
	}

}
