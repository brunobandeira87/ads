package asltexteditor.scanner;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.jface.text.rules.*;

public class ASLPartitionScanner extends RuleBasedPartitionScanner {
	public final static String ASL_COMMENT = "__asl_comment";
	public final static String ASL_SCOPE = "__asl_scope";
	public final static String ASL_PLAN = "__asl_plan";
	public final static String ASL_BELIEF = "__asl_belief";
	//public final static String ASL_TAG = "__asl_tag";

	public ASLPartitionScanner() {
		List<PatternRule> lista = new ArrayList<PatternRule>();
		
		IToken aslComment = new Token(ASL_COMMENT);
		IToken aslPlan = new Token(ASL_PLAN); 
		//IToken aslScope = new Token(ASL_SCOPE);

		lista.add(new MultiLineRule("/*", "*/", aslComment));
		lista.add(new SingleLineRule("//", "", aslComment));
		lista.add(new MultiLineRule("+", ".", aslPlan));
		lista.add(new MultiLineRule("-", ".", aslPlan));
		//lista.add(new MultiLineRule("<-", ".", aslScope));
		//lista.add(new SingleLineRule("+", ":", aslPlan));
		
		
		IPredicateRule[] allRules = new IPredicateRule[lista.size()];
		
		for (int i = 0; i < allRules.length; i++) {
			allRules[i] = lista.get(i);
		}
		
		setPredicateRules(allRules);

	}
}
