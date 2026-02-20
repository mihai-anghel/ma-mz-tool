package ro.anghel.mz.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PnlSkillBalls extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Icon BALL_ICON = new ImageIcon(
			PnlSkillBalls.class.getClassLoader().getResource("gui/goal.gif"));

	private static final Icon BALL_INACTIVE_ICON = new ImageIcon(
			PnlSkillBalls.class.getClassLoader()
					.getResource("gui/goal_inactive.gif"));

	private static final Dimension MIN_SIZE = new Dimension(
			BALL_ICON.getIconWidth(), BALL_ICON.getIconHeight());

	private static final Dimension MIN_SIZE_TEXT = new Dimension(
			3 * BALL_ICON.getIconWidth(), BALL_ICON.getIconHeight());

	static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder();

	private static final LayoutManager LAYOUT = new FlowLayout(FlowLayout.LEFT,
			3, 0);

	private final JLabel[] lblBalls = new JLabel[10];

	private JLabel lblBallsAsText = null;

	private static final String LBL_BALL = "LblBall";

	public PnlSkillBalls(final short aBalls) {
		setLayout(LAYOUT);
		setBorder(EMPTY_BORDER);
		setOpaque(false);
		for (int i = 0; i < lblBalls.length; ++i) {
			lblBalls[i] = new JLabel();
			add(lblBalls[i]);
			lblBalls[i].setHorizontalTextPosition(SwingConstants.LEFT);
			lblBalls[i].setHorizontalAlignment(SwingConstants.LEFT);
			lblBalls[i].setOpaque(false);
			lblBalls[i].setMinimumSize(MIN_SIZE);
			lblBalls[i].setPreferredSize(MIN_SIZE);
			lblBalls[i].setBorder(EMPTY_BORDER);
			lblBalls[i].setIconTextGap(0);
			lblBalls[i]
					.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblBalls[i].setName(LBL_BALL + i);
		}
		lblBallsAsText = new JLabel();
		add(lblBallsAsText);
		lblBallsAsText.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblBallsAsText.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBallsAsText.setOpaque(false);
		lblBallsAsText.setMinimumSize(MIN_SIZE_TEXT);
		lblBallsAsText.setPreferredSize(MIN_SIZE_TEXT);
		lblBallsAsText.setBorder(EMPTY_BORDER);
		setBalls(aBalls);
	}

	public PnlSkillBalls() {
		this((short) 0);
	}

	public void setBalls(short aBalls) {

		if (aBalls < 0) {
			aBalls = (short) 0;
		} else if (aBalls > lblBalls.length) {
			aBalls = (short) lblBalls.length;
		}
		lblBallsAsText.setText("(" + aBalls + ")");
		for (int i = 0; i < aBalls; ++i) {
			lblBalls[i].setIcon(BALL_ICON);
		}
		for (int i = aBalls; i < lblBalls.length; ++i) {
			lblBalls[i].setIcon(BALL_INACTIVE_ICON);
		}
	}

	public short getBalls() {
		short cnt = 0;
		for (final JLabel lblBall : lblBalls) {
			if (BALL_ICON.equals(lblBall.getIcon())) {
				++cnt;
			} else {
				break;
			}
		}
		return cnt;
	}

	public static short getLblBallIndex(final JLabel aLblBall) {
		final String name = aLblBall.getName();
		if (name.startsWith(LBL_BALL)) {
			final String strIdx = name.substring(LBL_BALL.length());
			return Short.parseShort(strIdx);
		}
		return -1;
	}

	public boolean isBallSet(final short aBall) {
		if (aBall < 0 || aBall > lblBalls.length) {
			return false;
		}
		return BALL_ICON.equals(lblBalls[aBall].getIcon());
	}

	public JLabel[] getLblBalls() {
		return lblBalls;
	}

}
