package asltexteditor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

import asltexteditor.editors.ColorManager;
import asltexteditor.editors.IASLColorConstants;

public class ASLScopeScanner extends RuleBasedScanner {

	public ASLScopeScanner(ColorManager manager) {
		//IToken asl = new Token(new TextAttribute(manager.getColor(IASLColorConstants.STRING)));
		
		IToken asl = new Token(new TextAttribute(manager.getColor(IASLColorConstants.ASL_COMMENT)));
		
		List<IRule> listRules = new ArrayList<IRule>();
		//listRules.add(new SingleLineRule("//", "\n", asl));
		listRules.add(new MultiLineRule("/*", "*/", asl));
		listRules.add(new WhitespaceRule(new ASLWhitespaceDetector()));
		IRule[] allRules = new IRule[listRules.size()];
		for (int i = 0; i < allRules.length; i++) {
			allRules[i] = listRules.get(i);
		}
		setRules(allRules);
		
		
		/*
		IRule[] rules = new IRule[3];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new ASLWhitespaceDetector());
		setRules(rules);
		 */
	}
}
