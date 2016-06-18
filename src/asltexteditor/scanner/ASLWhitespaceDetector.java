package asltexteditor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class ASLWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
