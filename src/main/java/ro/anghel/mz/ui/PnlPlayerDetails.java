package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Position;
import ro.anghel.view.ControllableView;

public class PnlPlayerDetails extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlPlayerDetails.xml";

	private static final String LBL_NAME = "LblName";

	private static final String LBL_NUMBER = "LblNumber";

	private static final String LBL_AGE = "LblAge";

	private static final String LBL_FOOT = "LblFoot";

	private static final String LBL_VALUE = "LblValue";

	private static final String LBL_SALARY = "LblSalary";

	private static final String LBL_HEIGHT = "LblHeight";

	private static final String LBL_WEIGHT = "LblWeight";

	private static final String LBL_COUNTRY = "LblCountry";

	private static final String LBL_JUNIOR = "LblJunior";

	private static final String LBL_TC = "LblTC";

	private static final String LBL_TC_INFO = "LblTCInfo";

	private static final String LBL_INJURY_SEVERITY = "LblInjurySeverity";

	private static final String LBL_INJURY = "LblInjury";

	private static final String LBL_INJURY_DAYS = "LblInjuryDays";

	private static final String LBL_GOALS = "LblGoals";

	private static final String LBL_YC = "LblYC";

	private static final String LBL_RC = "LblRC";

	private static final String LBL_ATC = "LblATC";

	private static final String LBL_STATUS_INFO = "LblStatusInfo";

	private static final String LBL_STATUS = "LblStatus";

	private static final String PNL_SPEED = "PnlSpeed";

	private static final String PNL_STAMINA = "PnlStamina";

	private static final String PNL_INTELLIGENCE = "PnlIntelligence";

	private static final String PNL_PASSING = "PnlPassing";

	private static final String PNL_SHOOTING = "PnlShooting";

	private static final String PNL_HEADING = "PnlHeading";

	private static final String PNL_KEEPING = "PnlKeeping";

	private static final String PNL_BALL_CONTROL = "PnlBallControl";

	private static final String PNL_TACKLING = "PnlTackling";

	private static final String PNL_AERIAL_PASSING = "PnlAerialPassing";

	private static final String PNL_SET_PLAYS = "PnlSetPlays";

	private static final String PNL_EXPERIENCE = "PnlExperience";

	private static final String PNL_FORM = "PnlForm";

	private static final Border EMPTY_BORDER = BorderFactory
			.createEmptyBorder(2, 4, 2, 4);

	private static final String LBL_POS_PREFIX = "Lbl";

	private static final String BTN_RESTORE_BALLS = "BtnRestoreBalls";

	public PnlPlayerDetails() {
		super(MODEL);
		final JLabel lblName = getLblName();
		Font font = lblName.getFont();
		lblName.setFont(font.deriveFont(Font.BOLD));
		lblName.setBorder(EMPTY_BORDER);
		for (final Position pos : Position.values()) {
			getLblForPosition(pos).setBackground(Color.decode(pos.color()));
		}

		final JLabel lblTC = getLblTC();
		font = lblTC.getFont().deriveFont(Font.BOLD, 8);
		lblTC.setFont(font);
	}

	public JLabel getLblNumber() {
		return getLabel(LBL_NUMBER);
	}

	public JLabel getLblName() {
		return getLabel(LBL_NAME);
	}

	public JLabel getLblAge() {
		return getLabel(LBL_AGE);
	}

	public JLabel getLblFoot() {
		return getLabel(LBL_FOOT);
	}

	public JLabel getLblValue() {
		return getLabel(LBL_VALUE);
	}

	public JLabel getLblSalary() {
		return getLabel(LBL_SALARY);
	}

	public JLabel getLblHeight() {
		return getLabel(LBL_HEIGHT);
	}

	public JLabel getLblWeight() {
		return getLabel(LBL_WEIGHT);
	}

	public JLabel getLblCountry() {
		return getLabel(LBL_COUNTRY);
	}

	public JLabel getLblJunior() {
		return getLabel(LBL_JUNIOR);
	}

	public JLabel getLblTC() {
		return getLabel(LBL_TC);
	}

	public JLabel getLblTCInfo() {
		return getLabel(LBL_TC_INFO);
	}

	public JLabel getLblInjurySeverity() {
		return getLabel(LBL_INJURY_SEVERITY);
	}

	public JLabel getLblInjury() {
		return getLabel(LBL_INJURY);
	}

	public JLabel getLblInjuryDays() {
		return getLabel(LBL_INJURY_DAYS);
	}

	public JLabel getLblGoals() {
		return getLabel(LBL_GOALS);
	}

	public JLabel getLblYC() {
		return getLabel(LBL_YC);
	}

	public JLabel getLblRC() {
		return getLabel(LBL_RC);
	}

	public JLabel getLblATC() {
		return getLabel(LBL_ATC);
	}

	public JLabel getLblStatusInfo() {
		return getLabel(LBL_STATUS_INFO);
	}

	public JLabel getLblStatus() {
		return getLabel(LBL_STATUS);
	}

	public PnlSkillBalls getPnlSpeed() {
		return (PnlSkillBalls) getPanel(PNL_SPEED);
	}

	public PnlSkillBalls getPnlStamina() {
		return (PnlSkillBalls) getPanel(PNL_STAMINA);
	}

	public PnlSkillBalls getPnlIntelligence() {
		return (PnlSkillBalls) getPanel(PNL_INTELLIGENCE);
	}

	public PnlSkillBalls getPnlPassing() {
		return (PnlSkillBalls) getPanel(PNL_PASSING);
	}

	public PnlSkillBalls getPnlShooting() {
		return (PnlSkillBalls) getPanel(PNL_SHOOTING);
	}

	public PnlSkillBalls getPnlHeading() {
		return (PnlSkillBalls) getPanel(PNL_HEADING);
	}

	public PnlSkillBalls getPnlKeeping() {
		return (PnlSkillBalls) getPanel(PNL_KEEPING);
	}

	public PnlSkillBalls getPnlBallControl() {
		return (PnlSkillBalls) getPanel(PNL_BALL_CONTROL);
	}

	public PnlSkillBalls getPnlTackling() {
		return (PnlSkillBalls) getPanel(PNL_TACKLING);
	}

	public PnlSkillBalls getPnlAerialPassing() {
		return (PnlSkillBalls) getPanel(PNL_AERIAL_PASSING);
	}

	public PnlSkillBalls getPnlSetPlays() {
		return (PnlSkillBalls) getPanel(PNL_SET_PLAYS);
	}

	public PnlSkillBalls getPnlExperience() {
		return (PnlSkillBalls) getPanel(PNL_EXPERIENCE);
	}

	public PnlSkillBalls getPnlForm() {
		return (PnlSkillBalls) getPanel(PNL_FORM);
	}

	public JLabel getLblForPosition(final Position position) {
		return getLabel(LBL_POS_PREFIX + position.name());
	}

	public AbstractButton gBtnRestoreBalls() {
		return getButton(BTN_RESTORE_BALLS);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		addLblBallsListener(getPnlSpeed(), aController);
		addLblBallsListener(getPnlStamina(), aController);
		addLblBallsListener(getPnlIntelligence(), aController);
		addLblBallsListener(getPnlPassing(), aController);
		addLblBallsListener(getPnlShooting(), aController);
		addLblBallsListener(getPnlHeading(), aController);
		addLblBallsListener(getPnlKeeping(), aController);
		addLblBallsListener(getPnlBallControl(), aController);
		addLblBallsListener(getPnlTackling(), aController);
		addLblBallsListener(getPnlAerialPassing(), aController);
		addLblBallsListener(getPnlSetPlays(), aController);
		addLblBallsListener(getPnlExperience(), aController);
		addLblBallsListener(getPnlForm(), aController);
		gBtnRestoreBalls().addActionListener((ActionListener) aController);
	}

	private static void addLblBallsListener(final PnlSkillBalls aPnlSkillBalls,
			final Controller<?, ?> aController) {
		final JLabel[] aLblBalls = aPnlSkillBalls.getLblBalls();
		for (int i = 0; i < aLblBalls.length; ++i) {
			aLblBalls[i].addMouseListener((MouseListener) aController);
		}
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		removeLblBallsListener(getPnlSpeed(), aController);
		removeLblBallsListener(getPnlStamina(), aController);
		removeLblBallsListener(getPnlIntelligence(), aController);
		removeLblBallsListener(getPnlPassing(), aController);
		removeLblBallsListener(getPnlShooting(), aController);
		removeLblBallsListener(getPnlHeading(), aController);
		removeLblBallsListener(getPnlKeeping(), aController);
		removeLblBallsListener(getPnlBallControl(), aController);
		removeLblBallsListener(getPnlTackling(), aController);
		removeLblBallsListener(getPnlAerialPassing(), aController);
		removeLblBallsListener(getPnlSetPlays(), aController);
		removeLblBallsListener(getPnlExperience(), aController);
		removeLblBallsListener(getPnlForm(), aController);
		gBtnRestoreBalls().removeActionListener((ActionListener) aController);
	}

	private static void removeLblBallsListener(
			final PnlSkillBalls aPnlSkillBalls,
			final Controller<?, ?> aController) {
		final JLabel[] aLblBalls = aPnlSkillBalls.getLblBalls();
		for (int i = 0; i < aLblBalls.length; ++i) {
			aLblBalls[i].removeMouseListener((MouseListener) aController);
		}
	}

}
