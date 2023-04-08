package getpizza.view;

import java.awt.*;
import javax.swing.*;

public class MainPage extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel avatar, buttonsPanel, cardsPanel;
	JButton Oferta, Pizza, Bebida, Postre, Carrito, Profile;

	public MainPage() {
		super(new BorderLayout());

		InitGUI();
	}

	void InitGUI() {
		buttonsPanel = createButtons();
		add(buttonsPanel, BorderLayout.WEST);
		add(ProductPanel(), BorderLayout.CENTER);

		setBounds(0, 0, 800, 480);
		setOpaque(false);
	}

	JPanel createAvatarImg() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(176, 150));
		panel.setPreferredSize(new Dimension(176, 150));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Get Pizza");
		title.setForeground(new Color(0xE0FAAB00, true));
		title.setFont(new Font(null, 1, 30));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setMaximumSize(new Dimension(176, 50));
		title.setPreferredSize(new Dimension(176, 50));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(title);

		JLabel image = new JLabel();
		image.setAlignmentX(CENTER_ALIGNMENT);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setMaximumSize(new Dimension(80, 80));
		image.setPreferredSize(new Dimension(80, 80));
		ImageIcon icon = new ImageIcon("Resource/avatar.png");
		icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		image.setIcon(icon);

		panel.add(image);

		return panel;
	}

	JPanel createButtons() {
		JPanel panel = new ButtonPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(176, 480));

		avatar = createAvatarImg();
		panel.add(avatar);

		panel.add(createButton(Oferta, "Oferta"));
		panel.add(createButton(Pizza, "Pizza"));
		panel.add(createButton(Bebida, "Bebida"));
		panel.add(createButton(Postre, "Postre"));
		panel.add(createButton(Carrito, "Carrito")); //TODO

		return panel;
	}

	JPanel ProductPanel() {
		JPanel panel = new JPanel(null);
		panel.setOpaque(false);

		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.setBounds(60, 50, 408, 390);
		cardsPanel.setOpaque(false);

		cardsPanel.add(new Products("Oferta"), "Oferta");
		cardsPanel.add(new Products("Pizza"), "Pizza");
		cardsPanel.add(new Products("Bebida"), "Bebida");
		cardsPanel.add(new Products("Postre"), "Postre");

		panel.add(cardsPanel);

		return panel;
	}

	JButton createButton(JButton button, String name) {
		ImageIcon icon = new ImageIcon("Resource/NormalButton.png");
		ImageIcon icon2 = new ImageIcon("Resource/RolloverButton.png");
		Image scaledImage = icon.getImage().getScaledInstance(176, 50, Image.SCALE_SMOOTH);
		Image scaledImage2 = icon2.getImage().getScaledInstance(176, 50, Image.SCALE_SMOOTH);

		button = new JButton(name, new ImageIcon(scaledImage));

		button.setBorderPainted(false);
		button.setForeground(Color.GREEN);
		button.setContentAreaFilled(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(176, 50));
		button.setPreferredSize(new Dimension(176, 50));
		button.setRolloverIcon(new ImageIcon(scaledImage2));
		button.setSelectedIcon(new ImageIcon(scaledImage2));
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);

		button.addActionListener((e) -> {
			CardLayout cl = (CardLayout) cardsPanel.getLayout();
			cl.show(cardsPanel, name);
		});
		return button;
	}
}

class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Image img;

	public ButtonPanel() {
		ImageIcon icon = new ImageIcon("Resource/Buttonbkg.jpg");
		img = icon.getImage();
		icon.setImage(img.getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
