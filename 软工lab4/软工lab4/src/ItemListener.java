import java.awt.event.ItemEvent;

public class ItemListener implements java.awt.event.ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String s = e.getItem().toString();
			System.out.println(s);
			Project.type=s;
		}
	}
}
