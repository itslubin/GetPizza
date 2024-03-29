package getpizza.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Registry extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextField Usuario, Nombre, Apellido, Correo, Tel, Direccion, Ciudad, Provincia, CodigoPostal;
	JPasswordField Password;
	JButton registry, cancel;
	int x = 222, y = 125;
	
	public Registry() {
		super(null);

		InitGUI();
	}

	void InitGUI() {
		setBounds(0, 0, 600, 450);
		setOpaque(false);

		setTitle();
		setButton();
		addTextField(Usuario = new JTextField(8), "Nombre de usuario:", x, y - 40, 120, 20);
		addTextField(Nombre = new JTextField(8), "Nombre:", x, y, 120, 20);
		addTextField(Apellido = new JTextField(8), "Apellido:", x + 140, y, 120, 20);
		addTextField(Correo = new JTextField(8), "Correo:", x, y + 40, 160, 20);
		addTextField(Tel = new JTextField(8), "Tel.:", x + 180, y + 40, 80, 20);
		addTextField(Password = new JPasswordField(8), "Contraseña:", x, y + 80, 150, 20);
		addTextField(Direccion = new JTextField(8), "Dirección:", x, y + 120, 260, 20);
		addTextField(Ciudad = new JTextField(8), "Ciudad:", x, y + 160, 120, 20);
		addTextField(Provincia = new JTextField(8), "Provincia:", x + 140, y + 160, 120, 20);
		addTextField(CodigoPostal = new JTextField(8), "Codigo Postal:", x, y + 200, 100, 20);

		setBackground();
	}

	void setTitle() {
		JLabel title = new JLabel("Registrarse");
		title.setForeground(new Color(0xE0FAAB00, true));
		title.setFont(new Font(null, 1, 30));
		title.setBounds(222, 45, 260, 30);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
	}

	void setButton() {
		registry = new JButton("Registrar");
		registry.setForeground(new Color(0, 0, 0));
		registry.setBounds(362, 380, 120, 35);
		registry.setBackground(new Color(255, 204, 153));
		add(registry);
		
		cancel = new JButton("Volver");
		cancel.setForeground(new Color(0, 0, 0));
		cancel.setBounds(222, 380, 120, 35);
		cancel.setBackground(new Color(134, 144, 138));

		add(cancel);
	}

	void setBackground() {
		JLabel bkg = new JLabel();
		bkg.setOpaque(true);
		bkg.setOpaque(true);
		bkg.setBackground(new Color(255, 255, 255, 160));
		bkg.setBounds(202, 40, 300, 400);
		add(bkg);
	}

	void addTextField(JTextField TextField, String name, int x, int y, int w, int l) {
		JLabel Text = new JLabel(name);
		Text.setForeground(new Color(0, 0, 0));
		Text.setBounds(x, y, w, 15);
		add(Text);

		TextField.setBounds(x, y + 15, w, l);
		TextField.setBackground(new Color(255, 255, 255, 220));
		add(TextField);
	}

	void addPasswordField(JPasswordField PasswordField, String name, int x, int y, int w, int l) {
		JLabel Text = new JLabel(name);
		Text.setForeground(new Color(255, 255, 255));
		Text.setBounds(x, y, w, 15);
		add(Text);

		PasswordField.setBounds(x, y + 15, w, l);
		PasswordField.setBackground(new Color(255, 255, 255, 220));
		add(PasswordField);
	}

	public Map<String, String> getInfo() {
		Map<String, String> res = new HashMap<>();
		res.put("Usuario", Usuario.getText());
		res.put("Nombre", Nombre.getText());
		res.put("Apellido", Apellido.getText());
		res.put("Correo", Correo.getText());
		res.put("Tel", Tel.getText());
		res.put("Direccion", Direccion.getText());
		res.put("Ciudad", Ciudad.getText());
		res.put("Provincia", Provincia.getText());
		res.put("CodigoPostal", CodigoPostal.getText());
		res.put("Password", new String(Password.getPassword()));

		return res;
	}

	public void setRegistryAction(ActionListener al) {
		registry.addActionListener(al);
	}

	public void setCancelAction(ActionListener al) {
		cancel.addActionListener(al);
	}

}
