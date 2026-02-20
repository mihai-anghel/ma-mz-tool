package ro.anghel.mz.ctr;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Injury;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Position;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.PnlPlayerDetails;
import ro.anghel.mz.ui.PnlSkillBalls;
import ro.anghel.mz.ui.TShirtLargeIcon;
import ro.anghel.mz.ui.renderer.ColorCalculator;
import ro.anghel.mz.ui.renderer.CountryFlags;
import ro.anghel.mz.ui.renderer.FormStatusIcons;
import ro.anghel.util.NumberFormatUtil;
import ro.anghel.util.StringFormatUtil;
import ro.anghel.view.ControllableView;

public class CtrPlayerInfo<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrPlayerInfo<T_PrevCtr>, T_PrevCtr, CtrPlayerInfo.CtrKey, CtrPlayerInfo.BoKey, CtrPlayerInfo.ViewKey>
		implements MouseListener, ActionListener {

	public enum CtrKey {
	}

	public enum BoKey {
		PLAYER
	}

	public enum ViewKey {
		PNL_PLAYER_DETAILS
	}

	public CtrPlayerInfo(final T_PrevCtr aPreviousController,
			final PnlPlayerDetails aPnlPlayerDetails, final Player aPlayer) {
		super(aPreviousController, new ControllableView[] { aPnlPlayerDetails },
				new Object[] { aPlayer });
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.PLAYER, aBusinessObjects[0]);
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_PLAYER_DETAILS, aViews[0]);
	}

	public Player getPlayer() {
		return (Player) getBusinessObject(BoKey.PLAYER);
	}

	protected PnlPlayerDetails getPnlPlayerDetails() {
		return (PnlPlayerDetails) getView(ViewKey.PNL_PLAYER_DETAILS);
	}

	@Override
	protected void disposeController() {
	}

	@Override
	protected void initViews() {
		final Player player = getPlayer();
		final PnlPlayerDetails pnl = getPnlPlayerDetails();

		pnl.getLblName().setText(player.getName());

		final JLabel lblNr = pnl.getLblNumber();
		lblNr.setText(String.valueOf(player.getNumber()));
		final Team team = player.getTeam();

		final Color priColor = Color.decode(team.getPriColor());
		final Color secColor = Color.decode(team.getSecColor());
		final int shirtPattern = team.getShirtPattern();
		final Color nrColor = ColorCalculator.calculateNrColor(shirtPattern,
				priColor, secColor);
		final TShirtLargeIcon tsIcon = new TShirtLargeIcon(shirtPattern,
				priColor, secColor, nrColor);
		lblNr.setIcon(tsIcon);
		lblNr.setForeground(tsIcon.getNumberColor());

		pnl.getLblAge().setText(String.valueOf(player.getAge()));

		final Foot foot = player.getFoot();
		pnl.getLblFoot().setText(foot.longName());

		final String curr = player.getTeam().getCurrency();
		pnl.getLblValue().setText(player.getValue() + " " + curr);
		pnl.getLblSalary().setText(player.getSalary() + " " + curr);
		pnl.getLblHeight().setText(player.getHeight() + " cm");
		pnl.getLblWeight().setText(player.getWeight() + " kg");

		JLabel lbl = pnl.getLblCountry();
		lbl.setText(player.getCountryLongName());
		lbl.setIcon(CountryFlags.getFlag(player.getCountryShortName()));

		pnl.getLblGoals().setText(String.valueOf(player.getGoals()));
		pnl.getLblYC().setText(String.valueOf(player.getYellowCards()));
		pnl.getLblRC().setText(String.valueOf(player.getRedCards()));

		ImageIcon icon = null;
		String atc = null;
		if (player.isAlreadyAtTrainingCamp()) {
			icon = new ImageIcon(CtrPlayerInfo.class.getClassLoader()
					.getResource("gui/icon_trainingcamp.gif"));
			atc = "Player Already Participated In Training Camp This Season";
		}
		lbl = pnl.getLblATC();
		lbl.setIcon(icon);
		lbl.setToolTipText(atc);

		pnl.getPnlSpeed().setBalls(player.getSpeed());
		pnl.getPnlStamina().setBalls(player.getStamina());
		pnl.getPnlIntelligence().setBalls(player.getIntelligence());
		pnl.getPnlPassing().setBalls(player.getPassing());
		pnl.getPnlShooting().setBalls(player.getShooting());
		pnl.getPnlHeading().setBalls(player.getHeading());
		pnl.getPnlKeeping().setBalls(player.getKeeping());
		pnl.getPnlBallControl().setBalls(player.getBallControl());
		pnl.getPnlTackling().setBalls(player.getTackling());
		pnl.getPnlAerialPassing().setBalls(player.getAerialPassing());
		pnl.getPnlSetPlays().setBalls(player.getSetPlays());
		pnl.getPnlExperience().setBalls(player.getExperience());
		pnl.getPnlForm().setBalls(player.getForm());

		final FormStatus status = player.getStatus();
		pnl.getLblStatus().setIcon(FormStatusIcons.getIcon(status));
		pnl.getLblStatusInfo().setText(status.longName());

		pnl.getLblJunior().setText(player.isJunior() ? "Junior" : "");

		final String tc = player.getTrainingCamp();
		icon = null;
		String tcNr = "";
		String tcD = null;
		if (!"".equals(tc)) {
			tcD = player.getTrainingCampDomain();
			icon = new ImageIcon(CtrPlayerInfo.class.getClassLoader()
					.getResource("gui/tc_" + tcD + ".gif"));
			tcNr = String.valueOf(player.getTrainingCampNr());
			tcD = "Training Camp - " + StringFormatUtil.capitalize(tcD);
		}
		lbl = pnl.getLblTC();
		lbl.setIcon(icon);
		lbl.setText(tcNr);
		lbl.setToolTipText(tcD);
		pnl.getLblTCInfo().setText(tc);

		icon = null;
		final Injury injury = player.getInjury();
		final String inj = injury.getInjuryName();
		String injDays = "";
		String injSev = "";
		if (!"".equals(inj)) {
			injSev = injury.getInjurySeverity().niceName();
			final String iconName = "Recover".equals(injSev)
					? "gui/injury_recover.gif"
					: "gui/injury_severe.gif";
			icon = new ImageIcon(
					CtrPlayerInfo.class.getClassLoader().getResource(iconName));
			final int d = injury.getInjuryDays();
			injDays = d + " Day" + (d != 1 ? "s" : "") + " Left";
		}
		lbl = pnl.getLblInjurySeverity();
		lbl.setIcon(icon);
		lbl.setText(injSev);
		pnl.getLblInjury().setText(inj);
		pnl.getLblInjuryDays().setText(injDays);

		updateLabelsForRatings(player);
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void mouseClicked(final MouseEvent aEvent) {
		if (aEvent.getClickCount() != 1) {
			return;
		}
		final Object src = aEvent.getSource();
		if (!(src instanceof final JLabel lblSrc)) {
			return;
		}
		final Container parent = lblSrc.getParent();
		if (!(parent instanceof final PnlSkillBalls pnlSrc)) {
			return;
		}
		final PnlPlayerDetails pnlDetails = getPnlPlayerDetails();
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlSpeed(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlStamina(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlIntelligence(),
				lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlPassing(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlShooting(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlHeading(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlKeeping(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlBallControl(),
				lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlTackling(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlAerialPassing(),
				lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlSetPlays(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlExperience(), lblSrc)) {
			return;
		}
		if (checkPnlSkillBalls(pnlSrc, pnlDetails.getPnlForm(), lblSrc)) {
			return;
		}
	}

	private boolean checkPnlSkillBalls(final PnlSkillBalls aPnlSrc,
			final PnlSkillBalls aPnlSkillBalls, final JLabel aLblBall) {
		if (aPnlSkillBalls == aPnlSrc) {
			final short ball = PnlSkillBalls.getLblBallIndex(aLblBall);
			if (ball > -1) {
				if (aPnlSrc.isBallSet(ball)) {
					aPnlSrc.setBalls(ball);
				} else {
					aPnlSrc.setBalls((short) (ball + 1));
				}
				changeRatings();
				return true;
			}
		}
		return false;
	}

	private void changeRatings() {
		final Player player = getPlayer();
		if (player == null) {
			return;
		}
		final PnlPlayerDetails pnlDetails = getPnlPlayerDetails();
		final short speed = pnlDetails.getPnlSpeed().getBalls();
		final short stamina = pnlDetails.getPnlStamina().getBalls();
		final short intell = pnlDetails.getPnlIntelligence().getBalls();
		final short pass = pnlDetails.getPnlPassing().getBalls();
		final short shoot = pnlDetails.getPnlShooting().getBalls();
		final short head = pnlDetails.getPnlHeading().getBalls();
		final short keep = pnlDetails.getPnlKeeping().getBalls();
		final short tech = pnlDetails.getPnlBallControl().getBalls();
		final short tack = pnlDetails.getPnlTackling().getBalls();
		final short cross = pnlDetails.getPnlAerialPassing().getBalls();
		final short setPl = pnlDetails.getPnlSetPlays().getBalls();
		final short exp = pnlDetails.getPnlExperience().getBalls();
		final short form = pnlDetails.getPnlForm().getBalls();

		final Injury injury = player.getInjury();
		final Player newPlayer = new Player(player.getId(), player.getTeam(),
				player.getNumber(), player.getName(), player.getValue(),
				player.getSalary(), player.getAge(), player.getHeight(),
				player.getWeight(), speed, stamina, intell, pass, shoot, head,
				keep, tech, tack, cross, setPl, exp, form, player.getFoot(),
				player.getCountryShortName(), player.getCountryLongName(),
				player.getStatus(), player.getGoals(), player.getYellowCards(),
				player.getRedCards(), player.getTrainingCamp(),
				player.getTrainingCampDomain(), player.getTrainingCampNr(),
				injury, player.isJunior(), player.isAlreadyAtTrainingCamp());

		updateLabelsForRatings(newPlayer);
	}

	private void updateLabelsForRatings(final Player aPlayer) {
		final double max = aPlayer.getMaxRating();
		final PnlPlayerDetails pnl = getPnlPlayerDetails();

		for (final Position pos : Position.values()) {
			final JLabel lbl = pnl.getLblForPosition(pos);
			final Font fontB = lbl.getFont().deriveFont(Font.BOLD);
			final Font fontP = lbl.getFont().deriveFont(Font.PLAIN);
			final double rt = pos.calculator().calculateRating(aPlayer);
			lbl.setText(NumberFormatUtil.FORMAT_2_2.numberFormat().format(rt));
			lbl.setFont(rt != max ? fontP : fontB);
		}
	}

	@Override
	public void mouseEntered(final MouseEvent aEvent) {
	}

	@Override
	public void mouseExited(final MouseEvent aEvent) {
	}

	@Override
	public void mousePressed(final MouseEvent aEvent) {
	}

	@Override
	public void mouseReleased(final MouseEvent aEvent) {
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		final PnlPlayerDetails pnl = getPnlPlayerDetails();
		if (pnl.gBtnRestoreBalls() == aEvent.getSource()) {
			final Player player = getPlayer();
			if (player != null) {
				pnl.getPnlSpeed().setBalls(player.getSpeed());
				pnl.getPnlStamina().setBalls(player.getStamina());
				pnl.getPnlIntelligence().setBalls(player.getIntelligence());
				pnl.getPnlPassing().setBalls(player.getPassing());
				pnl.getPnlShooting().setBalls(player.getShooting());
				pnl.getPnlHeading().setBalls(player.getHeading());
				pnl.getPnlKeeping().setBalls(player.getKeeping());
				pnl.getPnlBallControl().setBalls(player.getBallControl());
				pnl.getPnlTackling().setBalls(player.getTackling());
				pnl.getPnlAerialPassing().setBalls(player.getAerialPassing());
				pnl.getPnlSetPlays().setBalls(player.getSetPlays());
				pnl.getPnlExperience().setBalls(player.getExperience());
				pnl.getPnlForm().setBalls(player.getForm());
				updateLabelsForRatings(player);
			}
		}
	}

}
