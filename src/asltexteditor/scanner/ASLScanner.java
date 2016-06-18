package asltexteditor.scanner;


//TODO acho que vou apagar aqui 



import org.eclipse.jface.text.rules.*;

import asltexteditor.editors.ColorManager;
import asltexteditor.editors.IASLColorConstants;

import org.eclipse.jface.text.*;

public class ASLScanner extends RuleBasedScanner {

	public ASLScanner(ColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IASLColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new ASLWhitespaceDetector());

		setRules(rules);
	}
}
