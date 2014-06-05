
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class keyListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		String str = Frame2.editor.getText();
		System.out.println(str);// 输入的字母，后台处理
		Frame2.model.removeAllElements();
		
		List<String> l=new ArrayList<String>(); //传回的数据
		for(int i=0;i<5;i++){
			l.add(Double.toString(Math.random()));
		}
					Frame2.model.addElement(str);
		
		for(int i=0;i<5;i++){
			Frame2.model.addElement(l.get(i));
		}
		
	}

}
