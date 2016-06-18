package asltexteditor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;

import asltexteditor.editors.ColorManager;
import asltexteditor.editors.IASLColorConstants;

public class ASLPlanScanner extends RuleBasedScanner{
	
	public ASLPlanScanner(ColorManager manager){
		IToken trigger = new Token( new TextAttribute(manager.getColor(IASLColorConstants.ASL_COMMENT)));
		//WordRule wordRule = new WordRule(new );
		List<IRule> listRules = new ArrayList<IRule>();
		listRules.add(new MultiLineRule("+", ".", trigger));
		listRules.add(new MultiLineRule("-", ".", trigger));
		//listRules.add(new SingleLineRule("!", ":", trigger));
		//listRules.add(new SingleLineRule("?", ":", trigger));
		//listRules.add();
		
		IRule[] allRules = new IRule[listRules.size()];
		
		for (int i = 0; i < allRules.length; i++) {
			allRules[i] = listRules.get(i);
		}
		
		setRules(allRules);
	}
}
