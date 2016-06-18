package asltexteditor.editors;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class PlanRule extends MultiLineRule {
	
	public PlanRule(IToken token){
		super("+", ".", token);
	}
	
	
	
}
